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
package net.sf.appstatus.boot.demo.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.appstatus.boot.demo.common.ex.impl.ApiServerError;

/**
 * Jsons utils?
 * 
 * @author Franck Stephanovitch
 *
 */
public final class Jsons {
    /** Jackson object mapper. */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Constructor.
     */
    private Jsons() {
        // Nothing to do
    }

    /**
     * Convert object to json string
     * 
     * @param obj
     *            Object to convert
     * @return String json string
     */
    public static String toJson(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ApiServerError(e);
        }
    }

    /**
     * Convert json string to bean.
     * 
     * @param json
     *            json string
     * @param clazz
     *            object class to create
     * @return T object from json
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json.getBytes(StandardCharsets.UTF_8), clazz);
        } catch (IOException e) {
            throw new ApiServerError(e);
        }
    }

}
