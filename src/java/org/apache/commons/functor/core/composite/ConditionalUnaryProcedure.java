/*
 * Copyright 2003,2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.UnaryProcedure;

/**
 * A {@link UnaryProcedure UnaryProcedure} 
 * similiar to Java's "ternary" 
 * or "conditional" operator (<code>&#x3F; &#x3A;</code>).
 * Given a {@link UnaryPredicate predicate}
 * <i>p</i> and {@link UnaryProcedure procedures}
 * <i>q</i> and <i>r</i>, {@link #run runs}
 * <code>if(p.test(x)) { q.run(x); } else { r.run(x); }</code>.
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
public final class ConditionalUnaryProcedure implements UnaryProcedure, Serializable {

    // constructor
    // ------------------------------------------------------------------------

    public ConditionalUnaryProcedure(UnaryPredicate ifPred, UnaryProcedure thenPred, UnaryProcedure elsePred) {
        this.ifPred = ifPred;
        this.thenProc = thenPred;
        this.elseProc = elsePred;
    }
    
    // predicate interface
    // ------------------------------------------------------------------------
    public void run(Object obj) {
        if(ifPred.test(obj)) {
            thenProc.run(obj);
        } else {
            elseProc.run(obj);
        }
    }

    public boolean equals(Object that) {
        if(that instanceof ConditionalUnaryProcedure) {
            return equals((ConditionalUnaryProcedure)that);
        } else {
            return false;
        }
    }
    
    public boolean equals(ConditionalUnaryProcedure that) {
        return null != that && 
                (null == ifPred ? null == that.ifPred : ifPred.equals(that.ifPred)) &&
                (null == thenProc ? null == that.thenProc : thenProc.equals(that.thenProc)) &&
                (null == elseProc ? null == that.elseProc : elseProc.equals(that.elseProc));
    }
    
    public int hashCode() {
        int hash = "ConditionalUnaryProcedure".hashCode();
        if(null != ifPred) {
            hash <<= 4;
            hash ^= ifPred.hashCode();            
        }
        if(null != thenProc) {
            hash <<= 4;
            hash ^= thenProc.hashCode();            
        }
        if(null != elseProc) {
            hash <<= 4;
            hash ^= elseProc.hashCode();            
        }
        return hash;
    }
    
    public String toString() {
        return "ConditionalUnaryProcedure<" + ifPred + "?" + thenProc + ":" + elseProc + ">";
    }

    // attributes
    // ------------------------------------------------------------------------
    private UnaryPredicate ifPred = null;
    private UnaryProcedure thenProc = null;
    private UnaryProcedure elseProc = null;
}
