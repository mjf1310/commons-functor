/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.functor.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.functor.BaseFunctorTest;
import org.apache.commons.functor.UnaryProcedure;
import org.apache.commons.functor.core.NoOp;
import org.apache.commons.functor.core.RightIdentity;
import org.junit.Test;

/**
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public class TestLeftBoundProcedure extends BaseFunctorTest {

    // Functor Testing Framework
    // ------------------------------------------------------------------------

    @Override
    protected Object makeFunctor() {
        return new LeftBoundProcedure<Object>(NoOp.INSTANCE,"xyzzy");
    }

    // Tests
    // ------------------------------------------------------------------------

    @Test
    public void testRun() throws Exception {
        UnaryProcedure<Object> p = new LeftBoundProcedure<Object>(
                new BinaryFunctionBinaryProcedure<Object, Object>(RightIdentity.FUNCTION), "foo");
        p.run(Boolean.TRUE);
        p.run(Boolean.FALSE);
    }

    @Test
    public void testEquals() throws Exception {
        UnaryProcedure<Object> f = new LeftBoundProcedure<Object>(NoOp.INSTANCE, "xyzzy");
        assertEquals(f, f);
        assertObjectsAreEqual(f, new LeftBoundProcedure<Object>(NoOp.INSTANCE, "xyzzy"));
        assertObjectsAreNotEqual(f, new NoOp());
        assertObjectsAreNotEqual(f, new LeftBoundProcedure<Object>(
                new BinaryFunctionBinaryProcedure<Object, Object>(RightIdentity.FUNCTION), "xyzzy"));
        assertObjectsAreNotEqual(f, new LeftBoundProcedure<Object>(NoOp.INSTANCE, "foo"));
        assertObjectsAreNotEqual(f, new LeftBoundProcedure<Object>(NoOp.INSTANCE, null));
    }

    @Test
    public void testAdaptNull() throws Exception {
        assertNull(LeftBoundProcedure.bind(null,"xyzzy"));
    }

    @Test
    public void testAdapt() throws Exception {
        assertNotNull(LeftBoundProcedure.bind(new NoOp(),"xyzzy"));
        assertNotNull(LeftBoundProcedure.bind(new NoOp(),null));
    }
}
