/*
 *
 *  * Licensed to Bemobi, under one or more contributor license
 *  * agreements. See the NOTICE file distributed with this work for additional
 *  * information regarding copyright ownership. Bemobi licenses this
 *  * file to you under the Apache License, Version 2.0 (the "License"); you may
 *  * not use this file except in compliance with the License. You may obtain a
 *  * copy of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.bemobi.chainable.api;

/**
 * A <code>State</code> encapsulates states that can be returned by
 * commands.
 * <p>
 * {@link Command}s should either return <code>FINISHED</code> if the
 * processing of the given context has been completed, or return
 * <code>CONTINUE</code> if the processing of the given {@link Context} should
 * be delegated to a subsequent command in an enclosing {@link Chain}.
 *
 * @version $Id $
 */
public enum State {
    /**
     * Commands should return continue if the processing of the given
     * context should be delegated to a subsequent command in an enclosing chain.
     *
     */
    CONTINUE,

    /**
     * Commands should return finished if the processing of the given context
     * has been completed.
     *
     */
    FINISHED;
}
