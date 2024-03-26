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

import java.util.Objects;

/**
 * Represents a type-safe key for use in distribution operation contexts.
 * This key is used to store and retrieve values in a {@link DistributionOperationContext}
 * with type safety. The key includes an identifier and a type, ensuring that
 * values can be retrieved without the need for explicit casting.
 *
 * @param <T> The type of the value associated with this key.
 */
public final class DistributionContextKey<T> {

    private final String identifier;
    private final Class<T> type;

    /**
     * Private constructor for creating a new {@code DistributionContextKey}.
     * Use the static {@link #of(String, Class)} method to create instances.
     *
     * @param identifier A unique string identifier for this key.
     * @param type       The class of the type T.
     */
    private DistributionContextKey(String identifier, Class<T> type) {
        this.identifier = Objects.requireNonNull(identifier, "Identifier cannot be null");
        this.type = Objects.requireNonNull(type, "Type cannot be null");
    }

    /**
     * Returns the identifier of this key.
     *
     * @return The identifier string of this key.
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Returns the type associated with this key.
     *
     * @return The class representing the type T.
     */
    public Class<T> getType() {
        return this.type;
    }

    /**
     * Creates a new instance of a {@code DistributionContextKey} with the specified identifier and type.
     *
     * @param identifier A unique string identifier for the key.
     * @param type       The class of the type T.
     * @param <T>        The type of the value associated with the key.
     * @return A new {@code DistributionContextKey} instance.
     */
    public static <T> DistributionContextKey<T> of(String identifier, Class<T> type) {
        return new DistributionContextKey<>(identifier, type);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two {@code DistributionContextKey} objects are considered equal if they have the same identifier and type.
     *
     * @param other The reference object with which to compare.
     * @return {@code true} if this object is the same as the {@code other} argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        DistributionContextKey<?> that = (DistributionContextKey<?>) other;
        return this.identifier.equals(that.identifier) && this.type.equals(that.type);
    }

    /**
     * Returns a hash code value for this {@code DistributionContextKey}.
     * This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.identifier, this.type);
    }

    /**
     * Returns a string representation of the {@code DistributionContextKey} object.
     * This {@code toString} method returns a string that "textually represents" this object,
     * including its identifier and type.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return (
            "DistributionContextKey{" +
            "identifier=" +
            identifier +
            ", type=" +
            type.getName() +
            '}'
        );
    }
}
