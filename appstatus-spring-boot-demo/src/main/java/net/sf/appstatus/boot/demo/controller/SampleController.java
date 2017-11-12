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
package net.sf.appstatus.boot.demo.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.appstatus.boot.demo.common.ex.impl.ApiServerError;

/**
 * Sample controller.
 * 
 * @author Franck Stephanovitch
 *
 */
@RestController
public class SampleController {
    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(SampleController.class);
    /** Executor single thread. */
    private static final ExecutorService EXEC = Executors.newSingleThreadScheduledExecutor();

    /** String batch jobLauncher. */
    @Autowired
    private JobLauncher jobLauncher;

    /** One job. */
    @Autowired
    private Job oneJob;

    /**
     * On close, shutdown executor.
     */
    @PreDestroy
    public void cleanUp() {
        EXEC.shutdown();
    }

    /**
     * Welcome page.
     * 
     * @return String
     */
    @ResponseBody
    @GetMapping("/")
    public String home() {
        return "It works!";
    }

    /**
     * Error page.
     * 
     * @return String
     */
    @ResponseBody
    @GetMapping("/dummy")
    public String dummy() {
        throw new ApiServerError("Dummy test error");
    }

    /**
     * Invoke job one.
     * 
     * @return String
     */
    @ResponseBody
    @GetMapping("/invokejob")
    public String invokejob() {

        EXEC.execute(() -> {
            try {
                jobLauncher.run(oneJob, new JobParametersBuilder().toJobParameters());
            } catch (JobExecutionException e) {
                LOG.error("Error during job execution", e);
            }
        });

        return "Batch job has been invoked";
    }

}
