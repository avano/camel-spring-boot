/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.validator;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.engine.DefaultResourceLoader;
import org.apache.camel.spring.boot.CamelAutoConfiguration;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.urlhandler.pd.Handler;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Tests whether the ValidatorEndpoint.clearCachedSchema() can be executed when several sender threads are running.
 */
@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(
        classes = {
                CamelAutoConfiguration.class,
                ValidatorEndpointClearCachedSchemaTest.class
        }
)
public class ValidatorEndpointClearCachedSchemaTest extends ContextTestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(ValidatorEndpointClearCachedSchemaTest.class);

    @Test
    public void testClearCachedSchema() throws Exception {

        MockEndpoint mock = context.getEndpoint("mock:result", MockEndpoint.class);

        // send with 5 sender threads in parallel and call clear cache in
        // between
        ExecutorService senderPool = Executors.newFixedThreadPool(5);
        ExecutorService executorClearCache = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 5; i++) {
            senderPool.execute(new Sender());
            if (i == 2) {
                /*
                 * The clear cache thread calls xsdEndpoint.clearCachedSchema
                 */
                executorClearCache.execute(new ClearCache());
            }
        }

        senderPool.shutdown();
        executorClearCache.shutdown();

        senderPool.awaitTermination(4, TimeUnit.SECONDS);

        // expect at least 5 correct sent messages, the messages sent before
        // the clearCacheSchema method is called will fail with a validation
        // error and will nor result in an exchange
        mock.expectedMinimumMessageCount(5);
        mock.assertIsSatisfied();
    }

    @Bean
    protected RouteBuilder createRouteBuilder() throws Exception {
        context.getRegistry().bind(DefaultResourceLoader.RESOURCE_LOADER_KEY_PREFIX + "pd", new Handler());

        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start").to("validator:pd:somefile.xsd").convertBodyTo(String.class).to("log:after")
                        .to("mock:result");

            }
        };
    }

    private class Sender implements Runnable {

        private final String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + //
                                       "<p:TestMessage xmlns:p=\"http://apache.camel.org/test\">" + //
                                       "<MessageContent>MessageContent</MessageContent>" + //
                                       "</p:TestMessage>";

        private final byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        @Override
        public void run() {
            // send up to 5 messages
            for (int j = 0; j < 5; j++) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                template.sendBody("direct:start", messageBytes);
            }
        }

    }

    private class ClearCache implements Runnable {

        @Override
        public void run() {
            try {
                // start later after the first sender
                // threads are running
                Thread.sleep(200);
                clearCachedSchema();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void clearCachedSchema() throws Exception {
        CamelContext context = this.context;
        if (context == null) {
            return;
        }
        Collection<Endpoint> endpoints = context.getEndpoints();
        for (Endpoint endpoint : endpoints) {
            LOG.info("Endpoint URI: " + endpoint.getEndpointUri());
            if (endpoint.getEndpointUri().startsWith("validator:")) {
                ValidatorEndpoint xsltEndpoint = (ValidatorEndpoint) endpoint;
                xsltEndpoint.clearCachedSchema();
                LOG.info("schema cache cleared");
            }
        }
    }

}
