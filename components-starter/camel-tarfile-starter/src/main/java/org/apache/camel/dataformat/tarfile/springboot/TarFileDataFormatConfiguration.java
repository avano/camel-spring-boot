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
package org.apache.camel.dataformat.tarfile.springboot;

import javax.annotation.Generated;
import org.apache.camel.spring.boot.DataFormatConfigurationPropertiesCommon;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Archive files into tarballs or extract files from tarballs.
 * 
 * Generated by camel-package-maven-plugin - do not edit this file!
 */
@Generated("org.apache.camel.springboot.maven.SpringBootAutoConfigurationMojo")
@ConfigurationProperties(prefix = "camel.dataformat.tar-file")
public class TarFileDataFormatConfiguration
        extends
            DataFormatConfigurationPropertiesCommon {

    /**
     * Whether to enable auto configuration of the tarFile data format. This is
     * enabled by default.
     */
    private Boolean enabled;
    /**
     * If the tar file has more than one entry, the setting this option to true,
     * allows working with the splitter EIP, to split the data using an iterator
     * in a streaming mode.
     */
    private Boolean usingIterator = false;
    /**
     * If the tar file has more than one entry, setting this option to true,
     * allows to get the iterator even if the directory is empty
     */
    private Boolean allowEmptyDirectory = false;
    /**
     * If the file name contains path elements, setting this option to true,
     * allows the path to be maintained in the tar file.
     */
    private Boolean preservePathElements = false;
    /**
     * Set the maximum decompressed size of a tar file (in bytes). The default
     * value if not specified corresponds to 1 gigabyte. An IOException will be
     * thrown if the decompressed size exceeds this amount. Set to -1 to disable
     * setting a maximum decompressed size.
     */
    private Long maxDecompressedSize = 1073741824L;

    public Boolean getUsingIterator() {
        return usingIterator;
    }

    public void setUsingIterator(Boolean usingIterator) {
        this.usingIterator = usingIterator;
    }

    public Boolean getAllowEmptyDirectory() {
        return allowEmptyDirectory;
    }

    public void setAllowEmptyDirectory(Boolean allowEmptyDirectory) {
        this.allowEmptyDirectory = allowEmptyDirectory;
    }

    public Boolean getPreservePathElements() {
        return preservePathElements;
    }

    public void setPreservePathElements(Boolean preservePathElements) {
        this.preservePathElements = preservePathElements;
    }

    public Long getMaxDecompressedSize() {
        return maxDecompressedSize;
    }

    public void setMaxDecompressedSize(Long maxDecompressedSize) {
        this.maxDecompressedSize = maxDecompressedSize;
    }
}