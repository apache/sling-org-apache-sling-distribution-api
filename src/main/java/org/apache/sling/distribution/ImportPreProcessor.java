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

import java.util.Map;

/**
 * Interface for executing operations before the import of distribution content.
 * This acts as a hook for performing any necessary pre-import operations, such as
 * validation or initial setup, without modifying the properties of the content directly.
 * Implementations of this interface can carry out checks or other preparatory tasks
 * required before the content import process begins.
 */
public interface ImportPreProcessor {
    /**
     * Performs operations before the import of content. This method serves as a hook
     * for any pre-import tasks, like validation or setup, that need to be completed
     * prior to importing. It does not modify the input properties directly but can
     * throw an {@link ImportPreProcessException} to indicate any issues that would
     * prevent the content from being imported successfully.
     *
     * @param props properties defining the content to be imported
     * @throws ImportPreProcessException if there is an issue that prevents
     *         the content from being imported successfully
     */
    void process(Map<String, Object> props) throws ImportPreProcessException;
}
