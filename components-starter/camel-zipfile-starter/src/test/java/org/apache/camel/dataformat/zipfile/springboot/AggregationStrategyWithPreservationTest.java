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
package org.apache.camel.dataformat.zipfile.springboot;


import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.processor.aggregate.zipfile.ZipAggregationStrategy;
import org.apache.camel.spring.boot.CamelAutoConfiguration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.apache.camel.test.junit5.TestSupport;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.util.IOHelper;


@DirtiesContext
@CamelSpringBootTest
@SpringBootTest(
    classes = {
        CamelAutoConfiguration.class,
        AggregationStrategyWithPreservationTest.class,
        AggregationStrategyWithPreservationTest.TestConfiguration.class
    }
)
public class AggregationStrategyWithPreservationTest {

    private static final int EXPECTED_NO_FILES = 5;
    private static final String TEST_DIR = "target/out_AggregationStrategyWithPreservationTest";

    @Autowired
    ProducerTemplate template;
    
    @EndpointInject("mock:aggregateToZipEntry")
    MockEndpoint mock;
    
    @BeforeEach
    public void setUp() throws Exception {
        TestSupport.deleteDirectory(TEST_DIR);
    }

    @Test
    public void testSplitter() throws Exception {
        
        mock.expectedMessageCount(1);
        mock.assertIsSatisfied();

        File[] files = new File(TEST_DIR).listFiles();
        assertNotNull(files);
        assertTrue(files.length > 0, "Should be a file in " + TEST_DIR + " directory");

        File resultFile = files[0];
        Set<String> expectedZipFiles = new HashSet<>(
                Arrays.asList("another/hello.txt",
                        "other/greetings.txt",
                        "chiau.txt", "hi.txt", "hola.txt"));
        ZipInputStream zin = new ZipInputStream(new FileInputStream(resultFile));
        try {
            int fileCount = 0;
            for (ZipEntry ze = zin.getNextEntry(); ze != null; ze = zin.getNextEntry()) {
                if (!ze.isDirectory()) {
                    assertTrue(expectedZipFiles.remove(ze.toString()), "Found unexpected entry " + ze + " in zipfile");
                    fileCount++;
                }
            }

            assertEquals(AggregationStrategyWithPreservationTest.EXPECTED_NO_FILES, fileCount,
                    String.format("Zip file should contains %d files, got %d files",
                            AggregationStrategyWithPreservationTest.EXPECTED_NO_FILES, fileCount));
            assertEquals(0, expectedZipFiles.size(),
                    "Should have found all of the zip files in the file. Remaining: " + expectedZipFiles);
        } finally {
            IOHelper.close(zin);
        }
    }

    
    
    // *************************************
    // Config
    // *************************************

    @Configuration
    public static class TestConfiguration {

        @Bean
        public RouteBuilder routeBuilder() {
            return new RouteBuilder() {
                @Override
                public void configure() {
                    // Unzip file and Split it according to FileEntry
                    from("file:src/test/resources/org/apache/camel/aggregate/zipfile/data?delay=1000&noop=true&recursive=true")
                            .aggregate(new ZipAggregationStrategy(true, true))
                            .constant(true)
                            .completionFromBatchConsumer()
                            .eagerCheckCompletion()
                            .to("file:" + TEST_DIR)
                            .to("mock:aggregateToZipEntry")
                            .log("Done processing zip file: ${header.CamelFileName}");
                }
            };
        }
    }
    
   

}
