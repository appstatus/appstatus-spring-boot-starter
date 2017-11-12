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

import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cxf configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
@Configuration
public class ApiCxfConfig {
    /** Logging limit. */
    private static final int CXF_LOGGING_LIMIT = 1024;

    /**
     * Create logging feature.
     * 
     * @return LoggingFeature LoggingFeature bean
     */
    @Bean
    public LoggingFeature getLoggingFeature() {
        return new LoggingFeature(CXF_LOGGING_LIMIT);
    }

    /**
     * Create swagger2 feature.
     * 
     * @return Swagger2Feature Swagger2Feature bean
     */
    @Bean
    public Swagger2Feature getSwagger2Feature() {
        Swagger2Feature swagger = new Swagger2Feature();

        swagger.setLicense("appstatus-spring-boot licence");
        swagger.setLicenseUrl("#");
        swagger.setTitle("Appstatus Spring Boot");
        swagger.setVersion("1.0.0");

        return swagger;
    }

}
