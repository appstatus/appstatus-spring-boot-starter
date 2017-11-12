/*
 * AppStatus SpringBoot Starter 11.11.2017
 * Copyright (C) 2017 Capgemini and Contributors. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
*/
package net.sf.appstatus.boot.demo.api;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.sf.appstatus.boot.demo.common.Jsons;
import net.sf.appstatus.boot.demo.common.ex.AbstractApiException;
import net.sf.appstatus.boot.demo.common.model.Error;

/**
 * Jaxrs exception handler. <br />
 * Serialize AbstractApiException to {@link Error} json as response entity.
 * 
 * @author Franck Stephanovitch
 *
 */
@Provider
public class ApiExceptionHandler implements ExceptionMapper<AbstractApiException> {

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(AbstractApiException ex) {
        String errorToJson = Jsons.toJson(ex.toError());
        return Response.status(ex.getStatus())
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .entity(errorToJson)
                       .build();
    }

}
