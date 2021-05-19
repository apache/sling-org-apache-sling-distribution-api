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
package org.apache.sling.distribution.event;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.osgi.service.event.Event;

public class DistributionEventTest {

    private static final String PATH1 = "/test";
    private static final String DEEP_PATH = "/deep";
    private static final String DIST_TYPE = "ADD";
    private static final String NAME = "myagent";
    private static final String KIND = "agent";
    private static final String PKG_ID = "pkg-1";
    private DistributionEvent event;

    @Before
    public void before() {
        event = new DistributionEvent(PKG_ID, NAME, KIND, DIST_TYPE, new String[] {PATH1, DEEP_PATH}, new String[] {DEEP_PATH});
    }

    @Test
    public void testToEvent() {
        Event osgiEvent = event.toEvent(DistributionEventTopics.AGENT_PACKAGE_CREATED);
        assertThat(osgiEvent.getTopic(), equalTo(DistributionEventTopics.AGENT_PACKAGE_CREATED));
        assertThat((String)osgiEvent.getProperty(DistributionEventProperties.DISTRIBUTION_PACKAGE_ID), equalTo(PKG_ID));
        assertThat((String)osgiEvent.getProperty(DistributionEventProperties.DISTRIBUTION_COMPONENT_KIND), equalTo(KIND));
        assertThat((String)osgiEvent.getProperty(DistributionEventProperties.DISTRIBUTION_COMPONENT_NAME), equalTo(NAME));
        assertThat((String)osgiEvent.getProperty(DistributionEventProperties.DISTRIBUTION_TYPE), equalTo(DIST_TYPE));
        String[] paths = (String[])(osgiEvent.getProperty(DistributionEventProperties.DISTRIBUTION_PATHS));
        assertThat(Arrays.asList(paths), CoreMatchers.hasItems(PATH1));
    }
    
    @Test
    public void testFromEvent() {
        Event osgiEvent = event.toEvent(DistributionEventTopics.AGENT_PACKAGE_CREATED);
        DistributionEvent event2 = DistributionEvent.fromEvent(osgiEvent);
        assertThat(event2.getPackageId(), equalTo(PKG_ID));
        assertThat(event2.getComponentKind(), equalTo(KIND));
        assertThat(event2.getComponentName(), equalTo(NAME));
        assertThat(event2.getDistType(), equalTo(DIST_TYPE));
        String[] paths = event2.getDistPaths();
        assertThat(Arrays.asList(paths), CoreMatchers.hasItems(PATH1, DEEP_PATH));
        String[] deepPaths = event2.getDistDeepPaths();
        assertThat(Arrays.asList(deepPaths), CoreMatchers.hasItems(DEEP_PATH));
    }

}
