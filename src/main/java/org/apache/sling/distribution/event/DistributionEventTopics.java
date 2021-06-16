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

import org.osgi.annotation.versioning.ProviderType;

/**
 * an interface containing a list of the possible topics of events related to
 * distribution
 */
@ProviderType
public interface DistributionEventTopics {

    String EVENT_BASE = "org/apache/sling/distribution";

    /**
     * Event raised after the successful creation of a content package.
     */
    String AGENT_PACKAGE_CREATED = EVENT_BASE + "/agent/package/created";

    /**
     * Event raised after successfully storing a content package to the distribution
     * queues.
     */
    String AGENT_PACKAGE_QUEUED = EVENT_BASE + "/agent/package/queued";

    /**
     * Event raised after successfully distributing a content package from a
     * distribution queue.
     */
    String AGENT_PACKAGE_DISTRIBUTED = EVENT_BASE + "/agent/package/distributed";

    /**
     * Event raised when a content package could not be distributed and was removed
     * from a distribution queue.
     */
    String AGENT_PACKAGE_DROPPED = EVENT_BASE + "/agent/package/dropped";

    /**
     * Event raised after successfully importing a content package.
     */
    String IMPORTER_PACKAGE_IMPORTED = EVENT_BASE + "/importer/package/imported";
}
