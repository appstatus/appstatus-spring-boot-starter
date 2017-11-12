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
package net.sf.appstatus.boot.service;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AnnotationAttributes;

import net.sf.appstatus.boot.core.AbstractAppstatusImportAware;
import net.sf.appstatus.boot.core.AppstatusConfigBuilder;
import net.sf.appstatus.services.InProcessServiceManager;
import net.sf.appstatus.support.aop.AppStatusServiceInterceptor;

/**
 * 
 * AppStatusServiceConfig
 * 
 * @author Franck Stephanovitch
 *
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("net.sf.appstatus.boot.service.checker")
public class AppStatusServiceConfig extends AbstractAppstatusImportAware implements BeanFactoryPostProcessor {

    /** Configuration. */
    private Properties configuration;
    /** Pointcut expressions. */
    private List<String> pointcuts;

    /** Constructor. */
    public AppStatusServiceConfig() {
        super(EnableAppStatusService.class);
    }

    /**
     * Create bean {@link InProcessServiceManager}.
     * 
     * @return InProcessServiceManager service manager
     */
    @Bean("net.sf.appstatus.services.InProcessServiceManager")
    public InProcessServiceManager serviceManager() {
        InProcessServiceManager manager = new InProcessServiceManager();
        manager.setConfiguration(this.configuration);
        return manager;
    }

    /**
     * Create bean {@link AppStatusServiceInterceptor}.
     * 
     * @return AppStatusServiceInterceptor service interceptor
     */
    @Bean
    public AppStatusServiceInterceptor serviceInterceptor() {
        AppStatusServiceInterceptor interceptor = new AppStatusServiceInterceptor();
        interceptor.setServiceManager(serviceManager());
        return interceptor;
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
        this.configuration = configBuilder.set("services.log", "log")
                                          .set("services.log.format", "logFormat")
                                          .set("services.minMaxDelay", "minMaxDelay")
                                          .set("services.useThreadLocal", "useThreadLocal")
                                          .build();

        this.pointcuts = Arrays.asList(attributes.getStringArray("pointcuts"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#
     * postProcessBeanFactory(org.springframework.beans.factory.config.
     * ConfigurableListableBeanFactory)
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
        AtomicInteger i = new AtomicInteger();
        this.pointcuts.stream()
                      .map(this::createAdvisor)
                      .forEach((Advisor advisor) -> bf.registerSingleton(
                              "appstatus-service-advisor-" + i.incrementAndGet(), advisor));
    }

    /**
     * Create Advisor.
     * 
     * @param expression
     *            Pointcut expression
     * @return AspectJExpressionPointcutAdvisor advisor
     */
    private AspectJExpressionPointcutAdvisor createAdvisor(String expression) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setOrder(AspectJExpressionPointcutAdvisor.HIGHEST_PRECEDENCE);
        advisor.setAdvice(serviceInterceptor());
        advisor.setExpression(expression);
        return advisor;
    }

}
