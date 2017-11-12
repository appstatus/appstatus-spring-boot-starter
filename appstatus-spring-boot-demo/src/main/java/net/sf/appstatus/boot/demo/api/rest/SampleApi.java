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
package net.sf.appstatus.boot.demo.api.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import net.sf.appstatus.boot.demo.common.model.SampleObject;

/**
 * Sample Api.
 * 
 * @author Franck Stephanovitch
 *
 */
@Path("sample")
@Api(value = "/sample", tags = { "sample" })
public interface SampleApi {

    /**
     * Find object by id.
     * 
     * @param id
     *            id's object to find
     * @return SomeObject object found
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    SampleObject findObject(@PathParam("id") Long id);

    /**
     * Find object by id without cache.
     * 
     * @param id
     *            id's object to find
     * @return SomeObject object found
     */
    @GET
    @Path("/nocache/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    SampleObject findObjectNoCache(@PathParam("id") Long id);

}
