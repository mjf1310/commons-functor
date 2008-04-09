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

import org.apache.commons.functor.Predicate;

/**
 * A {@link Predicate Predicate}
 * similiar to Java's "ternary"
 * or "conditional" operator (<code>&#x3F; &#x3A;</code>).
 * Given three {@link Predicate predicates}
 * <i>p</i>, <i>q</i>, <i>r</i>,
 * {@link #test tests}
 * to
 * <code>p.test() ? q.test() : r.test()</code>.
 * <p>
 * Note that although this class implements
 * {@link Serializable}, a given instance will
 * only be truly <code>Serializable</code> if all the
 * underlying functors are.  Attempts to serialize
 * an instance whose delegates are not all
 * <code>Serializable</code> will result in an exception.
 * </p>
 * @version $Revision$ $Date$
 * @author Rodney Waldhoff
 */
public final class ConditionalPredicate implements Predicate, Serializable {
    // attributes
    // ------------------------------------------------------------------------
    private Predicate ifPred = null;
    private Predicate thenPred = null;
    private Predicate elsePred = null;

    // constructor
    // ------------------------------------------------------------------------
    /**
     * Create a new ConditionalPredicate.
     * @param ifPred if
     * @param thenPred then
     * @param elsePred else
     */
    public ConditionalPredicate(Predicate ifPred, Predicate thenPred, Predicate elsePred) {
        this.ifPred = ifPred;
        this.thenPred = thenPred;
        this.elsePred = elsePred;
    }

    // predicate interface
    // ------------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    public boolean test() {
        return ifPred.test() ? thenPred.test() : elsePred.test();
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object that) {
        return that == this || (that instanceof ConditionalPredicate && equals((ConditionalPredicate) that));
    }

    /**
     * Learn whether another ConditionalPredicate is equal to this.
     * @param that ConditionalPredicate to test
     * @return boolean
     */
    public boolean equals(ConditionalPredicate that) {
        return null != that
                && (null == ifPred ? null == that.ifPred : ifPred.equals(that.ifPred))
                && (null == thenPred ? null == that.thenPred : thenPred.equals(that.thenPred))
                && (null == elsePred ? null == that.elsePred : elsePred.equals(that.elsePred));
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        int hash = "ConditionalPredicate".hashCode();
        if (null != ifPred) {
            hash <<= 4;
            hash ^= ifPred.hashCode();
        }
        if (null != thenPred) {
            hash <<= 4;
            hash ^= thenPred.hashCode();
        }
        if (null != elsePred) {
            hash <<= 4;
            hash ^= elsePred.hashCode();
        }
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return "ConditionalPredicate<" + ifPred + "?" + thenPred + ":" + elsePred + ">";
    }

}