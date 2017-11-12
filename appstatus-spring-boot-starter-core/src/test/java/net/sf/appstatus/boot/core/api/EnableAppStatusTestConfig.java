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
package net.sf.appstatus.boot.core.api;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAttributes;

import net.sf.appstatus.boot.core.AbstractAppstatusImportAware;
import net.sf.appstatus.boot.core.AppstatusConfigBuilder;

@Configuration
public class EnableAppStatusTestConfig extends AbstractAppstatusImportAware {
    private AnnotationAttributes attributes;
    private Properties configuration;

    public EnableAppStatusTestConfig() {
        super(EnableAppStatusTestAnnotation.class);
    }

    @Bean
    public ConfigBean configBean() {
        ConfigBean bean = new ConfigBean();
        bean.setAttributes(this.attributes);
        bean.setConfiguration(this.configuration);
        return bean;
    }

    @Override
    protected void initialize(AnnotationAttributes attributes, AppstatusConfigBuilder configBuilder) {
        this.attributes = attributes;
        this.configuration = configBuilder.set("testAttribute", "testAttribute")
                                          .build();

    }

}
