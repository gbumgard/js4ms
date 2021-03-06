package org.js4ms.common.util.buffer.field;

/*
 * #%L
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *  
 * FixedBufferField.java [org.js4ms.jsdk:common]
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


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class FixedBufferField extends ArrayField<ByteBuffer> {

    public FixedBufferField(final int offset, final int size) {
        super(offset, size);
    }

    @Override
    public ByteBuffer get(final InputStream is) throws IOException {
        is.mark(this.offset+this.size);
        is.skip(this.offset);
        byte bytes[] = new byte[this.size];
        int count = is.read(bytes);
        is.reset();
        if (count != this.size) throw new EOFException();
        return ByteBuffer.wrap(bytes, 0, count);
    }

    @Override
    public final ByteBuffer get(final ByteBuffer buffer) {
        int position = buffer.position();
        buffer.position(this.offset);
        ByteBuffer result = buffer.slice();
        result.limit(Math.min(this.size,result.limit()));
        buffer.position(position);
        return result.slice();
    }

    @Override
    public final void set(final ByteBuffer buffer, final ByteBuffer value) {
        int position = buffer.position();
        buffer.position(this.offset);
        ByteBuffer source = value.slice();
        source.limit(Math.min(this.size,source.limit()));
        buffer.put(source);
        buffer.position(position);
    }

}
