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

import java.util.Set;

/**
 * Defines the contract for supplying a set of distribution paths. This interface
 * facilitates the retrieval of distribution paths based on the provided distribution
 * operation context, enabling dynamic and flexible configuration of the supplier.
 */
public interface DistributionPathSupplier {
    /**
     * Retrieves a set of distribution paths based on the provided distribution operation context.
     * The context can contain various types of information necessary for determining the distribution
     * paths, making this method highly adaptable to different requirements and criteria.
     *
     * @param distributionOperationContext The distribution operation context, encapsulating all
     *                                     necessary information in a type-safe manner for computing
     *                                     the distribution paths.
     * @return A set of strings representing the computed distribution paths.
     * @throws PathRetrievalException If there is an error in retrieving the distribution paths,
     *                                indicating failure to compute or retrieve the necessary paths.
     */
    Set<String> getDistributionPaths(DistributionOperationContext distributionOperationContext)
        throws PathRetrievalException;
}
