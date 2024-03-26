package org.apache.sling.distribution;

import junit.framework.TestCase;

import org.junit.Test;

public class DistributionContextKeyTest extends TestCase {
    @Test
    public void testToString() {
        DistributionContextKey<String> distributionContextKey = DistributionContextKey.of("test", String.class);
        System.out.println(distributionContextKey);
    }
}