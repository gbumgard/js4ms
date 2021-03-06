package org.js4ms.rest.handler;

/*
 * #%L
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *  
 * AddServerHeader.java [org.js4ms.jsdk:rest]
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

import org.js4ms.rest.header.SimpleMessageHeader;
import org.js4ms.rest.message.HeaderName;
import org.js4ms.rest.message.Response;

public class AddServerHeader implements ResponseHandler {

    private final String serverName;

    public AddServerHeader(final String serverName) {
        this.serverName = serverName;
    }

    @Override
    public void handleResponse(Response response) {
        response.setHeader(new SimpleMessageHeader(HeaderName.SERVER,this.serverName));
    }

}
