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
package org.apache.camel.component.mail.springboot;



import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mail.JavaMailSender;
import org.apache.camel.component.mail.MailAuthenticator;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;


@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        MailCustomMailSenderTest.class
    }
)
public class MailCustomMailSenderTest {

    
    @Autowired
    ProducerTemplate template;
    
    @Autowired
    CamelContext context;
    
    @EndpointInject("mock:result")
    MockEndpoint mock;
    
    private static boolean sent;

    @Bean("mySender")
    private MySender getMySender() {
        return new MySender();
    }

    @Test
    public void testSendWithCustomMailSender() {
        template.sendBody("smtp://claus@localhost?javaMailSender=#mySender", "Hello World");

        assertTrue(sent, "Should have used custom mail sender");
    }

    private static class MySender implements JavaMailSender {

        @Override
        public void send(MimeMessage mimeMessage) {
            sent = true;
        }

        @Override
        public Properties getJavaMailProperties() {
            return null;
        }

        @Override
        public void addAdditionalJavaMailProperty(String key, String value) {
        }

        @Override
        public void setJavaMailProperties(Properties javaMailProperties) {
        }

        @Override
        public void setHost(String host) {
        }

        @Override
        public String getHost() {
            return null;
        }

        @Override
        public void setPort(int port) {
        }

        @Override
        public int getPort() {
            return 0;
        }

        @Override
        public void setUsername(String username) {
        }

        @Override
        public String getUsername() {
            return null;
        }

        @Override
        public void setPassword(String password) {
        }

        @Override
        public String getPassword() {
            return null;
        }

        @Override
        public void setProtocol(String protocol) {
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public void setSession(Session session) {
        }

        @Override
        public Session getSession() {
            return null;
        }

        @Override
        public void setAuthenticator(MailAuthenticator authenticator) {
        }

        @Override
        public MailAuthenticator getAuthenticator() {
            return null;
        }
    }

}
