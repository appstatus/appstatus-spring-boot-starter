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
package net.sf.appstatus.boot.cache;

import java.io.IOException;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ClassPathResource;

import net.sf.appstatus.boot.core.AbstractAppstatusImportAware;
import net.sf.appstatus.boot.core.AppstatusConfigBuilder;
import net.sf.appstatus.support.spring.cache.AppStatusCacheManager;

/**
 * AppStatus cache configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
@EnableCaching
@Configuration
public class AppStatusCacheConfig extends AbstractAppstatusImportAware {

    /** ClassPathResource config file. */
    private String configFile;

    /**
     * Constructor.
     */
    public AppStatusCacheConfig() {
        super(EnableAppStatusCache.class);
    }

    /**
     * Create bean {@link AppStatusCacheManager}.
     * 
     * @return AppStatusCacheManager Appstatus cache manager.
     * @throws IOException
     *             Configuration exception
     */
    @Bean
    public AppStatusCacheManager appStatusCacheManager() throws IOException {
        AppStatusCacheManager acm = new AppStatusCacheManager();
        acm.setCacheManager(new JCacheCacheManager(jCacheFactoryBean().getObject()));
        return acm;
    }

    /**
     * Create bean {@link JCacheManagerFactoryBean}.
     * 
     * @return JCacheManagerFactoryBean jCache manager factory bean.
     * @throws IOException
     *             Configuration exception
     */
    @Bean
    public JCacheManagerFactoryBean jCacheFactoryBean() throws IOException {
        JCacheManagerFactoryBean cmfb = new JCacheManagerFactoryBean();
        cmfb.setCacheManagerUri(new ClassPathResource(this.configFile).getURI());
        return cmfb;
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
        this.configFile = attributes.getString("configFile");

    }
}
