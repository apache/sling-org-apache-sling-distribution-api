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

import static org.apache.sling.distribution.event.DistributionEventProperties.DISTRIBUTION_COMPONENT_KIND;
import static org.apache.sling.distribution.event.DistributionEventProperties.DISTRIBUTION_COMPONENT_NAME;
import static org.apache.sling.distribution.event.DistributionEventProperties.DISTRIBUTION_PACKAGE_ID;
import static org.apache.sling.distribution.event.DistributionEventProperties.DISTRIBUTION_PATHS;
import static org.apache.sling.distribution.event.DistributionEventProperties.DISTRIBUTION_TYPE;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.event.Event;

public class DistributionEvent {

    private final String packageId;
    private final String componentName;
    private final String componentKind;
    private final String distType;
    private final String[] distPaths;

    public DistributionEvent(
            String packageId,
            String componentName,
            String componentKind,
            String distType,
            String[] distPaths) {
        this.packageId = packageId;
        this.componentName = componentName;
        this.componentKind = componentKind;
        this.distType = distType;
        this.distPaths = distPaths;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getComponentKind() {
        return componentKind;
    }

    public String getDistType() {
        return distType;
    }

    public String[] getDistPaths() {
        return distPaths;
    }

    public Event toEvent(String topic) {
        Dictionary<String, Object> props = new Hashtable<String, Object>();
        props.put(DISTRIBUTION_PACKAGE_ID, packageId);
        props.put(DISTRIBUTION_COMPONENT_NAME, componentName);
        props.put(DISTRIBUTION_COMPONENT_KIND, componentKind);
        props.put(DISTRIBUTION_TYPE, distType);
        props.put(DISTRIBUTION_PATHS, distPaths);
        return new Event(topic, props);
    }

    public static DistributionEvent fromEvent(Event event) {
        return new DistributionEvent(
                event.getProperty(DISTRIBUTION_PACKAGE_ID).toString(), 
                event.getProperty(DISTRIBUTION_COMPONENT_NAME).toString(),
                event.getProperty(DISTRIBUTION_COMPONENT_KIND).toString(),
                event.getProperty(DISTRIBUTION_TYPE).toString(),
                (String[])event.getProperty(DISTRIBUTION_PATHS));
    }
}
