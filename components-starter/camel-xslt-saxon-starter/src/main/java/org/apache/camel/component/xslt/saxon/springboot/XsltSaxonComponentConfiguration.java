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
package org.apache.camel.component.xslt.saxon.springboot;

import java.util.Map;
import javax.annotation.Generated;
import javax.xml.transform.URIResolver;
import net.sf.saxon.Configuration;
import org.apache.camel.component.xslt.TransformerFactoryConfigurationStrategy;
import org.apache.camel.component.xslt.XsltUriResolverFactory;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Transform XML payloads using an XSLT template using Saxon.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.xslt-saxon")
public class XsltSaxonComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the xslt-saxon component. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * Cache for the resource content (the stylesheet file) when it is loaded.
     * If set to false Camel will reload the stylesheet file on each message
     * processing. This is good for development. A cached stylesheet can be
     * forced to reload at runtime via JMX using the clearCachedStylesheet
     * operation.
     */
    private Boolean contentCache = true;
    /**
     * Whether the producer should be started lazy (on the first message). By
     * starting lazy you can use this to allow CamelContext and routes to
     * startup in situations where a producer may otherwise fail during starting
     * and cause the route to fail being started. By deferring this startup to
     * be lazy then the startup failure can be handled during routing messages
     * via Camel's routing error handlers. Beware that when the first message is
     * processed then creating and starting the producer may take a little time
     * and prolong the total processing time of the processing.
     */
    private Boolean lazyStartProducer = false;
    /**
     * Whether autowiring is enabled. This is used for automatic autowiring
     * options (the option must be marked as autowired) by looking up in the
     * registry to find if there is a single instance of matching type, which
     * then gets configured on the component. This can be used for automatic
     * configuring JDBC data sources, JMS connection factories, AWS Clients,
     * etc.
     */
    private Boolean autowiredEnabled = true;
    /**
     * To use a custom Saxon configuration. The option is a
     * net.sf.saxon.Configuration type.
     */
    private Configuration saxonConfiguration;
    /**
     * To set custom Saxon configuration properties
     */
    private Map<String, Object> saxonConfigurationProperties;
    /**
     * Allows you to use a custom net.sf.saxon.lib.ExtensionFunctionDefinition.
     * You would need to add camel-saxon to the classpath. The function is
     * looked up in the registry, where you can comma to separate multiple
     * values to lookup.
     */
    private String saxonExtensionFunctions;
    /**
     * Feature for XML secure processing (see javax.xml.XMLConstants). This is
     * enabled by default. However, when using Saxon Professional you may need
     * to turn this off to allow Saxon to be able to use Java extension
     * functions.
     */
    private Boolean secureProcessing = true;
    /**
     * To use a custom XSLT transformer factory, specified as a FQN class name
     */
    private String transformerFactoryClass;
    /**
     * A configuration strategy to apply on freshly created instances of
     * TransformerFactory. The option is a
     * org.apache.camel.component.xslt.TransformerFactoryConfigurationStrategy
     * type.
     */
    private TransformerFactoryConfigurationStrategy transformerFactoryConfigurationStrategy;
    /**
     * To use a custom UriResolver. Should not be used together with the option
     * 'uriResolverFactory'. The option is a javax.xml.transform.URIResolver
     * type.
     */
    private URIResolver uriResolver;
    /**
     * To use a custom UriResolver which depends on a dynamic endpoint resource
     * URI. Should not be used together with the option 'uriResolver'. The
     * option is a org.apache.camel.component.xslt.XsltUriResolverFactory type.
     */
    private XsltUriResolverFactory uriResolverFactory;

    public Boolean getContentCache() {
        return contentCache;
    }

    public void setContentCache(Boolean contentCache) {
        this.contentCache = contentCache;
    }

    public Boolean getLazyStartProducer() {
        return lazyStartProducer;
    }

    public void setLazyStartProducer(Boolean lazyStartProducer) {
        this.lazyStartProducer = lazyStartProducer;
    }

    public Boolean getAutowiredEnabled() {
        return autowiredEnabled;
    }

    public void setAutowiredEnabled(Boolean autowiredEnabled) {
        this.autowiredEnabled = autowiredEnabled;
    }

    public Configuration getSaxonConfiguration() {
        return saxonConfiguration;
    }

    public void setSaxonConfiguration(Configuration saxonConfiguration) {
        this.saxonConfiguration = saxonConfiguration;
    }

    public Map<String, Object> getSaxonConfigurationProperties() {
        return saxonConfigurationProperties;
    }

    public void setSaxonConfigurationProperties(
            Map<String, Object> saxonConfigurationProperties) {
        this.saxonConfigurationProperties = saxonConfigurationProperties;
    }

    public String getSaxonExtensionFunctions() {
        return saxonExtensionFunctions;
    }

    public void setSaxonExtensionFunctions(String saxonExtensionFunctions) {
        this.saxonExtensionFunctions = saxonExtensionFunctions;
    }

    public Boolean getSecureProcessing() {
        return secureProcessing;
    }

    public void setSecureProcessing(Boolean secureProcessing) {
        this.secureProcessing = secureProcessing;
    }

    public String getTransformerFactoryClass() {
        return transformerFactoryClass;
    }

    public void setTransformerFactoryClass(String transformerFactoryClass) {
        this.transformerFactoryClass = transformerFactoryClass;
    }

    public TransformerFactoryConfigurationStrategy getTransformerFactoryConfigurationStrategy() {
        return transformerFactoryConfigurationStrategy;
    }

    public void setTransformerFactoryConfigurationStrategy(
            TransformerFactoryConfigurationStrategy transformerFactoryConfigurationStrategy) {
        this.transformerFactoryConfigurationStrategy = transformerFactoryConfigurationStrategy;
    }

    public URIResolver getUriResolver() {
        return uriResolver;
    }

    public void setUriResolver(URIResolver uriResolver) {
        this.uriResolver = uriResolver;
    }

    public XsltUriResolverFactory getUriResolverFactory() {
        return uriResolverFactory;
    }

    public void setUriResolverFactory(XsltUriResolverFactory uriResolverFactory) {
        this.uriResolverFactory = uriResolverFactory;
    }
}