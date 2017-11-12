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
package net.sf.appstatus.boot.batch;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.jdbc.core.JdbcTemplate;

import net.sf.appstatus.batch.jdbc.BatchDao;
import net.sf.appstatus.batch.jdbc.JdbcBatchManager;
import net.sf.appstatus.boot.core.AbstractAppstatusImportAware;
import net.sf.appstatus.boot.core.AppstatusConfigBuilder;

/**
 * AppStatus batch configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
@Configuration
public class AppStatusBatchConfig extends AbstractAppstatusImportAware {

    /** AppStatus batch table name. */
    private String tableName;

    /** AppStatus batch configuration. */
    private Properties configuration;

    /** AppStatus batch dao class. */
    private Class<? extends BatchDao> daoClass;

    /** JdbcTemplate. */
    @Autowired(required = false)
    private JdbcTemplate template;

    /** JdbcTemplate. */
    @Autowired
    private DataSource dataSource;

    /**
     * Constructor.
     */
    public AppStatusBatchConfig() {
        super(EnableAppStatusBatch.class);
    }

    /**
     * Create bean JdbcBatchManager.
     * 
     * @return JdbcBatchManager Appstatus Batch jdbc manager.
     * @throws ReflectiveOperationException
     *             Configuration exception
     */
    @Bean(name = "appstatusJdbcBatchManager", initMethod = "init")
    public JdbcBatchManager appstatusJdbcBatchManager() throws ReflectiveOperationException {
        JdbcBatchManager jdbcBatchManager = new JdbcBatchManager();
        jdbcBatchManager.setBatchDao(batchDao());
        jdbcBatchManager.setConfiguration(this.configuration);

        return jdbcBatchManager;
    }

    /**
     * Create bean BatchDao.
     * 
     * @return BatchDao instance of BatchDao
     * @throws ReflectiveOperationException
     *             Configuration exception
     */
    @Bean
    public BatchDao batchDao() throws ReflectiveOperationException {
        BatchDao dao = daoClass.newInstance();

        if (template != null) {
            dao.setJdbcTemplate(this.template);
        } else {
            dao.setJdbcTemplate(new JdbcTemplate(this.dataSource));
        }

        if (StringUtils.isNotBlank(this.tableName)) {
            dao.setTableName(this.tableName);
        }

        return dao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.appstatus.boot.core.AbstractAppstatusImportAware#initialize(org.
     * springframework.core.annotation.AnnotationAttributes,
     * net.sf.appstatus.boot.core.AppstatusConfigBuilder)
     */
    @Override
    protected void initialize(AnnotationAttributes attributes, AppstatusConfigBuilder builder) {
        this.configuration = builder.set("batch.logInterval", "logInterval")
                                    .set("batch.zombieInterval", "zombieInterval")
                                    .build();

        this.tableName = attributes.getString("tableName");
        this.daoClass = attributes.getClass("batchDaoClass");
    }

}
