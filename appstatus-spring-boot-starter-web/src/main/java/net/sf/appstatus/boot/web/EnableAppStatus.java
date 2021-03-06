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
package net.sf.appstatus.boot.web;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

/**
 * EnabledAppStatus web starter.
 * 
 * @author Franck Stephanovitch
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AppStatusConfig.class)
public @interface EnableAppStatus {

    /**
     * Servlet urlMappings.
     * 
     * @return String[] urlMappings
     */
    @AliasFor("urlMappings")
    String[] value() default AppStatusWebConstants.DEFAULT_URL_MAPPINGS;

    /**
     * Servlet urlMappings.
     * 
     * @return String[] urlMappings
     */
    @AliasFor("value")
    String[] urlMappings() default AppStatusWebConstants.DEFAULT_URL_MAPPINGS;

}
