package com.google.common.util.concurrent;

import org.junit.Assert;
import org.junit.Test;

public class BadTester {

    @Test
    public void badTest() {
        Assert.fail("should not run");
    }
}
