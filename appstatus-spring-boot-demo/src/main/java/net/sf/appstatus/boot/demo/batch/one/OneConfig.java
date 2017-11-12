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
package net.sf.appstatus.boot.demo.batch.one;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.appstatus.boot.demo.batch.one.step1.OneItemProcessor;
import net.sf.appstatus.boot.demo.batch.one.step1.OneItemReader;
import net.sf.appstatus.boot.demo.batch.one.step1.OneItemWritter;
import net.sf.appstatus.boot.demo.common.model.SampleObject;

/**
 * One job configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
@Configuration
@EnableBatchProcessing
public class OneConfig {

    /** Step one chunk. */
    private static final int STEP_ONE_CHUNK = 20;

    /** JobBuilderFactory. */
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    /** StepBuilderFactory. */
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /** OneItemReader. */
    @Autowired
    private OneItemReader reader;

    /** OneItemProcessor. */
    @Autowired
    private OneItemProcessor processor;

    /** OneItemWritter. */
    @Autowired
    private OneItemWritter writter;

    /** OneExecutionListener. */
    @Autowired
    private OneExecutionListener listener;

    /**
     * Create job one.
     * 
     * @return Job job one
     */
    @Bean
    public Job oneJob() {
        return jobBuilderFactory.get("one-job") //
                                .incrementer(new RunIdIncrementer()) //
                                .listener(listener) //
                                .flow(oneStep()) //
                                .end() //
                                .build();
    }

    /**
     * Create job one, step one.
     * 
     * @return Step step one
     */
    @Bean
    public Step oneStep() {
        return stepBuilderFactory.get("one-step") //
                                 .<Integer, SampleObject>chunk(STEP_ONE_CHUNK) //
                                 .reader(reader)
                                 .processor(processor)
                                 .writer(writter) //
                                 .build();
    }

}
