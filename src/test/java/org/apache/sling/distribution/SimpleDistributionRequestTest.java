/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.distribution;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SimpleDistributionRequestTest {

    @Test
    public void testSanitiseNoPaths() {
        SimpleDistributionRequest req = new SimpleDistributionRequest(DistributionRequestType.ADD);
        assertThat(req.getPaths().length, equalTo(0));
    }
    
    @Test
    public void testSanitiseSingleEmptyPath() {
        SimpleDistributionRequest req = new SimpleDistributionRequest(DistributionRequestType.ADD, "");
        assertThat(req.getPaths().length, equalTo(0));
    }
    
    @Test
    public void testSanitiseOkPaths() {
        SimpleDistributionRequest req = new SimpleDistributionRequest(DistributionRequestType.ADD, "test", "test2");
        assertThat(req.getPaths().length, equalTo(2));
    }
    
    @Test
    public void testSanitiseSomeEmptyPaths() {
        SimpleDistributionRequest req = new SimpleDistributionRequest(DistributionRequestType.ADD, "test", "", "test2");
        assertThat(req.getPaths().length, equalTo(2));
        assertThat(req.getPaths()[0], equalTo("test"));
        assertThat(req.getPaths()[1], equalTo("test2"));
    }
    
    @Test
    public void testSanitiseDeepPaths() {
        Set<String> deepPaths = new HashSet<>(Arrays.asList("test", "", "test2"));
        SimpleDistributionRequest req = new SimpleDistributionRequest(DistributionRequestType.ADD, new String[]{"test"} , deepPaths);
        assertThat(req.isDeep("test"), equalTo(true));
        assertThat(req.isDeep("test2"), equalTo(true));
    }
}
