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
package org.apache.camel.component.milo.browse.springboot;

import javax.annotation.Generated;
import org.apache.camel.component.milo.client.MiloClientConfiguration;
import org.apache.camel.component.milo.client.MiloClientConnectionManager;
import org.apache.camel.spring.boot.ComponentConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Connect to OPC UA servers using the binary protocol for browsing the node
 * tree.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.component.milo-browse")
public class MiloBrowseComponentConfiguration
        extends
            ComponentConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the milo-browse component. This
     * is enabled by default.
     */
    private Boolean enabled;
    /**
     * A virtual client id to force the creation of a new connection instance
     */
    private String clientId;
    /**
     * All default options for client configurations. The option is a
     * org.apache.camel.component.milo.client.MiloClientConfiguration type.
     */
    private MiloClientConfiguration configuration;
    /**
     * A suffix for endpoint URI when discovering
     */
    private String discoveryEndpointSuffix;
    /**
     * An alternative discovery URI
     */
    private String discoveryEndpointUri;
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
     * A set of allowed security policy URIs. Default is to accept all and use
     * the highest.
     */
    private String allowedSecurityPolicies;
    /**
     * The application name
     */
    private String applicationName = "Apache Camel adapter for Eclipse Milo";
    /**
     * The application URI
     */
    private String applicationUri = "http://camel.apache.org/EclipseMilo/Client";
    /**
     * Channel lifetime in milliseconds
     */
    private Long channelLifetime;
    /**
     * The name of the key in the keystore file
     */
    private String keyAlias;
    /**
     * The key password
     */
    private String keyPassword;
    /**
     * The keystore password
     */
    private String keyStorePassword;
    /**
     * The key store type
     */
    private String keyStoreType;
    /**
     * The URL where the key should be loaded from
     */
    private String keyStoreUrl;
    /**
     * The maximum number of pending publish requests
     */
    private Long maxPendingPublishRequests;
    /**
     * The maximum number of bytes a response message may have
     */
    private Long maxResponseMessageSize;
    /**
     * Instance for managing client connections. The option is a
     * org.apache.camel.component.milo.client.MiloClientConnectionManager type.
     */
    private MiloClientConnectionManager miloClientConnectionManager;
    /**
     * Override the server reported endpoint host with the host from the
     * endpoint URI.
     */
    private Boolean overrideHost = false;
    /**
     * The product URI
     */
    private String productUri = "http://camel.apache.org/EclipseMilo";
    /**
     * The requested publishing interval in milliseconds
     */
    private Double requestedPublishingInterval;
    /**
     * Request timeout in milliseconds
     */
    private Long requestTimeout;
    /**
     * Session name
     */
    private String sessionName;
    /**
     * Session timeout in milliseconds
     */
    private Long sessionTimeout;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public MiloClientConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MiloClientConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getDiscoveryEndpointSuffix() {
        return discoveryEndpointSuffix;
    }

    public void setDiscoveryEndpointSuffix(String discoveryEndpointSuffix) {
        this.discoveryEndpointSuffix = discoveryEndpointSuffix;
    }

    public String getDiscoveryEndpointUri() {
        return discoveryEndpointUri;
    }

    public void setDiscoveryEndpointUri(String discoveryEndpointUri) {
        this.discoveryEndpointUri = discoveryEndpointUri;
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

    public String getAllowedSecurityPolicies() {
        return allowedSecurityPolicies;
    }

    public void setAllowedSecurityPolicies(String allowedSecurityPolicies) {
        this.allowedSecurityPolicies = allowedSecurityPolicies;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationUri() {
        return applicationUri;
    }

    public void setApplicationUri(String applicationUri) {
        this.applicationUri = applicationUri;
    }

    public Long getChannelLifetime() {
        return channelLifetime;
    }

    public void setChannelLifetime(Long channelLifetime) {
        this.channelLifetime = channelLifetime;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public String getKeyStoreType() {
        return keyStoreType;
    }

    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    public String getKeyStoreUrl() {
        return keyStoreUrl;
    }

    public void setKeyStoreUrl(String keyStoreUrl) {
        this.keyStoreUrl = keyStoreUrl;
    }

    public Long getMaxPendingPublishRequests() {
        return maxPendingPublishRequests;
    }

    public void setMaxPendingPublishRequests(Long maxPendingPublishRequests) {
        this.maxPendingPublishRequests = maxPendingPublishRequests;
    }

    public Long getMaxResponseMessageSize() {
        return maxResponseMessageSize;
    }

    public void setMaxResponseMessageSize(Long maxResponseMessageSize) {
        this.maxResponseMessageSize = maxResponseMessageSize;
    }

    public MiloClientConnectionManager getMiloClientConnectionManager() {
        return miloClientConnectionManager;
    }

    public void setMiloClientConnectionManager(
            MiloClientConnectionManager miloClientConnectionManager) {
        this.miloClientConnectionManager = miloClientConnectionManager;
    }

    public Boolean getOverrideHost() {
        return overrideHost;
    }

    public void setOverrideHost(Boolean overrideHost) {
        this.overrideHost = overrideHost;
    }

    public String getProductUri() {
        return productUri;
    }

    public void setProductUri(String productUri) {
        this.productUri = productUri;
    }

    public Double getRequestedPublishingInterval() {
        return requestedPublishingInterval;
    }

    public void setRequestedPublishingInterval(
            Double requestedPublishingInterval) {
        this.requestedPublishingInterval = requestedPublishingInterval;
    }

    public Long getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Long requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }
}