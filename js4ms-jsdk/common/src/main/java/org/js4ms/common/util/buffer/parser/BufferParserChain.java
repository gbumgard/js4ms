package org.js4ms.common.util.buffer.parser;

/*
 * #%L
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *  
 * BufferParserChain.java [org.js4ms.jsdk:common]
 * %%
 * Copyright (C) 2009 - 2014 Cisco Systems, Inc.
 * %%
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
 * #L%
 */


import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;

import org.js4ms.common.exception.ParseException;



public class BufferParserChain<T> {

    final LinkedList<BufferParser<T>> chain = new LinkedList<BufferParser<T>>();
    
    public void add(final BufferParser<T> parser) {
        this.chain.add(parser);
    }
    
    public void remove(final BufferParser<T> parser) {
        this.chain.remove(parser);
    }

    public T parse(final ByteBuffer buffer) throws ParseException, MissingParserException {
        Iterator<BufferParser<T>> iter = this.chain.iterator();
        while (iter.hasNext()) {
            T object = iter.next().parse(buffer);
            if (object != null) return object;
        }
        return null;
    }

}
