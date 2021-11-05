package com.google.common.reflect;

import org.junit.Assert;
import org.junit.Test;

public class BadTester {

    @Test
    public void badTest() {
        Assert.fail("should not run");
    }
}
