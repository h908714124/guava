package com.google.common.eventbus;

import org.junit.Assert;
import org.junit.Test;

public class BadTester {

    @Test
    public void badTest() {
        Assert.fail("should not run");
    }
}
