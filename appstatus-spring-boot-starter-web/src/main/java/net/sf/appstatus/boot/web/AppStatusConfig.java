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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAttributes;

import net.sf.appstatus.boot.core.AbstractAppstatusImportAware;
import net.sf.appstatus.boot.core.AppstatusConfigBuilder;
import net.sf.appstatus.core.AppStatus;
import net.sf.appstatus.core.check.ICheck;
import net.sf.appstatus.core.property.IPropertyProvider;
import net.sf.appstatus.support.spring.SpringObjectInstantiationListener;
import net.sf.appstatus.web.IPage;

/**
 * AppStatus web configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
@Configuration
@ComponentScan({ "net.sf.appstatus.boot.web.checker", "net.sf.appstatus.boot.web.provider" })
@ServletComponentScan("net.sf.appstatus.boot.web")
public class AppStatusConfig extends AbstractAppstatusImportAware {

    /** Servlet urlMappings. */
    private String[] urlMappings;

    /** Checkers. */
    @Autowired(required = false)
    private List<ICheck> checkers;

    /** PropertyProviders. */
    @Autowired(required = false)
    private List<IPropertyProvider> propertyProviders;

    /** Custom pages. */
    @Autowired(required = false)
    private List<IPage> customPages;

    public AppStatusConfig() {
        super(EnableAppStatus.class);
    }

    /**
     * Create AppStatus bean.
     * 
     * @return AppStatus bean
     */
    @Bean(name = AppStatusWebConstants.BEAN_NAME, initMethod = "init")
    public AppStatus appstatus() {
        return new AppStatusBuilder().checkers(checkers)
                                     .propertyProviders(propertyProviders)
                                     .objectInstanciationListener(listener())
                                     .build();
    }

    @Bean
    public ServletRegistrationBean appstatusServletRegistration() {
        return new AppStatusServletBuilder().urlMappings(urlMappings)
                                            .customPages(customPages)
                                            .servletRegistrationBean();
    }

    /**
     * Create SpringObjectInstantiationListener bean.
     * 
     * @return SpringObjectInstantiationListener bean
     */
    @Bean
    public SpringObjectInstantiationListener listener() {
        return new SpringObjectInstantiationListener();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.appstatus.boot.core.AbstractAppstatusImportAware#initialize(org.
     * springframework.core.annotation.AnnotationAttributes,
     * net.sf.appstatus.boot.core.AppstatusConfigBuilder)
     */
    @Override
    protected void initialize(AnnotationAttributes attributes, AppstatusConfigBuilder configBuilder) {
        this.urlMappings = attributes.getStringArray("urlMappings");
    }

}
