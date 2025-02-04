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
package org.apache.camel.dataformat.swift.mx.springboot;

import javax.annotation.Generated;
import org.apache.camel.spring.boot.DataFormatConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Encode and decode SWIFT MX messages.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.dataformat.swift-mx")
public class SwiftMxDataFormatConfiguration
        extends
            DataFormatConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the swiftMx data format. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * Refers to a specific configuration to use when marshalling a message to
     * lookup from the registry.
     */
    private String writeConfigRef;
    /**
     * The flag indicating that messages must be marshalled in a JSON format.
     */
    private Boolean writeInJson = false;
    /**
     * The type of MX message to produce when unmarshalling an input stream. If
     * not set, it will be automatically detected from the namespace used.
     */
    private String readMessageId;
    /**
     * Refers to a specific configuration to use when unmarshalling an input
     * stream to lookup from the registry.
     */
    private String readConfigRef;

    public String getWriteConfigRef() {
        return writeConfigRef;
    }

    public void setWriteConfigRef(String writeConfigRef) {
        this.writeConfigRef = writeConfigRef;
    }

    public Boolean getWriteInJson() {
        return writeInJson;
    }

    public void setWriteInJson(Boolean writeInJson) {
        this.writeInJson = writeInJson;
    }

    public String getReadMessageId() {
        return readMessageId;
    }

    public void setReadMessageId(String readMessageId) {
        this.readMessageId = readMessageId;
    }

    public String getReadConfigRef() {
        return readConfigRef;
    }

    public void setReadConfigRef(String readConfigRef) {
        this.readConfigRef = readConfigRef;
    }
}