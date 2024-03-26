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

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the context for a distribution operation, encapsulating various parameters and settings
 * in a type-safe manner. This context is used to store and retrieve information needed for performing
 * distribution operations, allowing for flexible and dynamic configuration.
 */
public class DistributionOperationContext {

    private final Map<DistributionContextKey<?>, Object> values = new HashMap<>();

    /**
     * Stores a value in the context associated with a specific key.
     *
     * @param <T>   The type of the value to store in the context.
     * @param key   The key under which the value is stored. Must not be null.
     * @param value The value to store in the context. Can be null.
     */
    public <T> void put(DistributionContextKey<T> key, T value) {
        this.values.put(key, value);
    }

    /**
     * Retrieves a value from the context associated with a specific key.
     *
     * @param <T> The expected type of the value to retrieve.
     * @param key The key under which the value is stored. Must not be null.
     * @return The value associated with the specified key, or {@code null} if no value is found.
     */
    public <T> T get(DistributionContextKey<T> key) {
        return key.getType().cast(this.values.get(key));
    }

    /**
     * Checks whether the context contains a value for the specified key.
     *
     * @param key The key to check in the context. Must not be null.
     * @return {@code true} if the context contains a value for the key, {@code false} otherwise.
     */
    public boolean containsKey(DistributionContextKey<?> key) {
        return this.values.containsKey(key);
    }

    /**
     * Checks whether the context is empty.
     *
     * @return {@code true} if the context contains no key-value mappings, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    /**
     * Returns a string representation of the DistributionOperationContext.
     * The string representation consists of a list of key-value pairs enclosed in braces ("{}").
     *
     * @return A string representation of the context's contents.
     */
    @Override
    public String toString() {
        return values
            .entrySet()
            .stream()
            .map(entry -> entry.getKey().toString() + "=" + entry.getValue().toString())
            .collect(Collectors.joining(", ", "DistributionOperationContext{", "}"));
    }
}
