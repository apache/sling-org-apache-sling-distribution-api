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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests for {@link DistributionResponse}
 */
public class DistributionResponseTest {
    @Test
    public void defaultDistributionResponse() {
        DistributionResponse res = new DistributionResponse() {
            @Override public boolean isSuccessful() {
                return false;
            }

            @Nonnull @Override public DistributionRequestState getState() {
                return null;
            }

            @Nullable @Override public String getMessage() {
                return null;
            }
        };
        assertNotNull(res.getDistributionInfo());
        assertEquals("", res.getDistributionInfo().getId());
    }
    
    @Test
    public void emptyDistributionResponse() {
        DistributionResponse res = new TestDistributionResponse(DistributionRequestState.DISTRIBUTED, "");
        assertNotNull(res.getDistributionInfo());
        assertEquals("", res.getDistributionInfo().getId());
    }
    
    @Test
    public void nullDistributionResponse() {
        DistributionResponse res = new TestDistributionResponse(DistributionRequestState.DISTRIBUTED, "", null);
        assertNull(res.getDistributionInfo());
    }

    @Test
    public void nonEmptyDistributionResponse() {
        DistributionResponse res1 = new TestDistributionResponse(DistributionRequestState.DISTRIBUTED, "success",
            new DistributionResponseInfo() {
                @Override public String getId() {
                    return "res1";
                }
            });

        assertNotNull(res1.getDistributionInfo());
        assertEquals("res1", res1.getDistributionInfo().getId());
    }
    
    class TestDistributionResponse implements DistributionResponse {
        private final DistributionRequestState state;

        private final String message;
        private final DistributionResponseInfo info;
        
        TestDistributionResponse(DistributionRequestState state, String message) {
            this(state, message, DistributionResponseInfo.NONE);
        }

        TestDistributionResponse(DistributionRequestState state, String message, DistributionResponseInfo info) {
            this.state = state;
            this.message = message;
            this.info = info;
        }

        @Override public boolean isSuccessful() {
            return false;
        }

        @Nonnull @Override public DistributionRequestState getState() {
            return state;
        }

        @Nullable @Override public String getMessage() {
            return message;
        }
        
        @Override public DistributionResponseInfo getDistributionInfo() {
            return info;
        }
    }
}
