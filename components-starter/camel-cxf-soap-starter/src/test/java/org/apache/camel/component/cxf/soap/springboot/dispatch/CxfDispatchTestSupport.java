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
package org.apache.camel.component.cxf.soap.springboot.dispatch;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.Endpoint;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.apache.camel.component.cxf.common.CXFTestSupport;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration;
import org.apache.hello_world_soap_http.GreeterImpl;

@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(classes = {
                           CamelAutoConfiguration.class, 
                           CxfDispatchTestSupport.class,
                           CxfAutoConfiguration.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class CxfDispatchTestSupport {

    
      
    protected static final String DISPATCH_NS = "http://camel.apache.org/cxf/jaxws/dispatch";
    protected static final String INVOKE_NAME = "Invoke";
    protected static final String INVOKE_ONEWAY_NAME = "InvokeOneWay";

    protected static final String PAYLOAD_TEMPLATE
            = "<ns1:greetMe xmlns:ns1=\"http://apache.org/hello_world_soap_http/types\"><ns1:requestType>%s</ns1:requestType></ns1:greetMe>";
    protected static final String PAYLOAD_ONEWAY_TEMPLATE
            = "<ns1:greetMeOneWay xmlns:ns1=\"http://apache.org/hello_world_soap_http/types\"><ns1:requestType>%s</ns1:requestType></ns1:greetMeOneWay>";
    protected static final String MESSAGE_TEMPLATE
            = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body>"
              + PAYLOAD_TEMPLATE
              + "</soap:Body></soap:Envelope>";
    protected static final String MESSAGE_ONEWAY_TEMPLATE
            = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body>"
              + PAYLOAD_ONEWAY_TEMPLATE
              + "</soap:Body></soap:Envelope>";
    private static DocumentBuilderFactory documentBuilderFactory;
    
    static int port = CXFTestSupport.getPort1();

    protected Endpoint endpoint;
    
    @BeforeEach
    public void startService() {
        Object implementor = new GreeterImpl();
        String address = "/"
                         + getClass().getSimpleName() + "/SoapContext/GreeterPort";
        endpoint = Endpoint.publish(address, implementor);
    }

    @AfterEach
    public void stopService() {
        if (endpoint != null) {
            endpoint.stop();
        }
    }

    protected static String getResponseType(Element node) {
        NodeList nodes = node.getElementsByTagNameNS("http://apache.org/hello_world_soap_http/types", "responseType");
        if (nodes != null && nodes.getLength() == 1) {
            Node c = nodes.item(0).getFirstChild();
            if (c != null) {
                return c.getNodeValue();
            }
        }
        return null;
    }

    protected static synchronized DocumentBuilderFactory getDocumentBuilderFactory() {
        if (documentBuilderFactory == null) {
            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setIgnoringElementContentWhitespace(true);
            documentBuilderFactory.setIgnoringComments(true);
        }
        return documentBuilderFactory;
    }
    
    @Configuration
    class ServletConfiguration {
        @Bean
        public ServletWebServerFactory servletWebServerFactory() {
            return new UndertowServletWebServerFactory(port);
        }

    }
    
}
