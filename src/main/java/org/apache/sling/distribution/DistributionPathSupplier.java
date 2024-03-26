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

import java.util.List;
import java.util.Set;

/**
 * Defines the contract for supplying a set of distribution paths. This interface
 * allows for the retrieval of distribution paths based on a list of initial paths,
 * which can be used to configure or initialize the supplier.
 */
public interface DistributionPathSupplier {
    /**
     * Retrieves a set of distribution paths based on the provided initial paths.
     * This method allows for dynamic adjustment of the paths based on initial
     * configuration or other criteria.
     *
     * @param initialPaths a list of strings representing initial paths that might influence
     *                     the retrieval or generation of the distribution paths.
     * @return a set of strings representing the distribution paths.
     * @throws PathRetrievalException if there is an error in retrieving the distribution paths.
     */
    Set<String> getDistributionPaths(List<String> initialPaths) throws PathRetrievalException;
}
