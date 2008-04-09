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
package org.apache.commons.functor.core.composite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.functor.BinaryProcedure;

/**
 * A {@link BinaryProcedure BinaryProcedure}
 * that {@link BinaryProcedure#run runs} an ordered
 * sequence of {@link BinaryProcedure BinaryProcedures}.
 * When the sequence is empty, this procedure is does
 * nothing.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 *
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public class BinarySequence implements BinaryProcedure, Serializable {
    // attributes
    // ------------------------------------------------------------------------
    private List list = new ArrayList();

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new BinarySequence.
     */
    public BinarySequence() {
    }

    /**
     * Create a new BinarySequence.
     * @param p BinaryProcedure to add
     */
    public BinarySequence(BinaryProcedure p) {
        then(p);
    }

    /**
     * Create a new BinarySequence.
     * @param p BinaryProcedure to add
     * @param q BinaryProcedure to add
     */
    public BinarySequence(BinaryProcedure p, BinaryProcedure q) {
        then(p);
        then(q);
    }

    /**
     * Fluently add a BinaryProcedure.
     * @param p BinaryProcedure to add
     * @return this
     */
    public BinarySequence then(BinaryProcedure p) {
        list.add(p);
        return this;
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public void run(Object left, Object right) {
        for (ListIterator iter = list.listIterator(list.size()); iter.hasPrevious();) {
            ((BinaryProcedure) iter.previous()).run(left, right);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object that) {
        return that == this || (that instanceof BinarySequence && equals((BinarySequence) that));
    }

    /**
     * Learn whether another BinarySequence is equal to this.
     * @param that BinarySequence to test
     * @return boolean
     */
    public boolean equals(BinarySequence that) {
        // by construction, list is never null
        return null != that && list.equals(that.list);
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        // by construction, list is never null
        return "BinarySequence".hashCode() ^ list.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "BinarySequence<" + list + ">";
    }

}