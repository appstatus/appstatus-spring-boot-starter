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
package net.sf.appstatus.boot.core;

import java.util.Properties;

import org.springframework.core.annotation.AnnotationAttributes;

/**
 * AppStatus config builder.
 * 
 * @author Franck Stephanovitch
 *
 */
public class AppstatusConfigBuilder {
    /** Configuration properties. */
    private Properties config = new Properties();
    /** Configuration attributes. */
    private AnnotationAttributes attributes;

    /**
     * Constructor.
     * 
     * @param attributes
     *            configuration attributes
     */
    public AppstatusConfigBuilder(AnnotationAttributes attributes) {
        this.attributes = attributes;
    }

    /**
     * Set entry to configuration properties.
     * 
     * @param configKey
     *            configuration key
     * @param attributeKey
     *            attibutes key
     */
    public AppstatusConfigBuilder set(String configKey, String attributeKey) {
        config.put(configKey, String.valueOf(attributes.get(attributeKey)));
        return this;
    }

    /**
     * AppStatus configuration.
     * 
     * @return Properties AppStatus configuration
     */
    public Properties build() {
        return (Properties) config.clone();
    }

}
