/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import junit.framework.TestCase;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static com.google.common.util.concurrent.Uninterruptibles.awaitUninterruptibly;

/** @author Charles Fry */
@GwtCompatible(emulated = true)
public class MapMakerTest extends TestCase {

//    public void testNullParameters() {
//        NullPointerTester tester = new NullPointerTester();
//        tester.testAllPublicInstanceMethods(new MapMaker());
//    }

    public void testDummy() {
    }

    @GwtIncompatible // threads
    static final class DelayingIdentityLoader<T> implements Function<T, T> {
        private final CountDownLatch delayLatch;

        DelayingIdentityLoader(CountDownLatch delayLatch) {
            this.delayLatch = delayLatch;
        }

        @Override
        public T apply(T key) {
            awaitUninterruptibly(delayLatch);
            return key;
        }
    }

    /*
     * TODO(cpovirk): eliminate duplication between these tests and those in LegacyMapMakerTests and
     * anywhere else
     */

    /** Tests for the builder. */
    public static class MakerTest extends TestCase {
        public void testInitialCapacity_negative() {
            MapMaker maker = new MapMaker();
            try {
                maker.initialCapacity(-1);
                fail();
            } catch (IllegalArgumentException expected) {
            }
        }

        // TODO(cpovirk): enable when ready
        public void xtestInitialCapacity_setTwice() {
            MapMaker maker = new MapMaker().initialCapacity(16);
            try {
                // even to the same value is not allowed
                maker.initialCapacity(16);
                fail();
            } catch (IllegalArgumentException expected) {
            }
        }

        public void testReturnsPlainConcurrentHashMapWhenPossible() {
            Map<?, ?> map = new MapMaker().initialCapacity(5).makeMap();
            assertTrue(map instanceof ConcurrentHashMap);
        }
    }
}
