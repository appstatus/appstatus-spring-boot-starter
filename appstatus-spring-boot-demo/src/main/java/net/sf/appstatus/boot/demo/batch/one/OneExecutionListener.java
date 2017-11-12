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

import java.util.UUID;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import net.sf.appstatus.boot.demo.batch.StatusJobs;

/**
 * One job execution listener.
 * 
 * @author Franck Stephanovitch
 *
 */
@Component
public class OneExecutionListener extends JobExecutionListenerSupport {
    /** Job name. */
    private static final String JOB_NAME = "One job";
    /** Job group. */
    private static final String JOB_GROUP = "element";

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.batch.core.listener.JobExecutionListenerSupport#beforeJob
     * (org.springframework.batch.core.JobExecution)
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        String jobUUID = UUID.randomUUID()
                             .toString();
        StatusJobs.getMonitor(JOB_NAME, JOB_GROUP, jobUUID);
        jobExecution.getExecutionContext()
                    .putString("jobUUID", jobUUID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.batch.core.listener.JobExecutionListenerSupport#afterJob(
     * org.springframework.batch.core.JobExecution)
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            StatusJobs.getMonitor(JOB_NAME, JOB_GROUP, jobExecution.getExecutionContext())
                      .done();
        }

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            StatusJobs.getMonitor(JOB_NAME, JOB_GROUP, jobExecution.getExecutionContext())
                      .fail("Execution failure");
        }
    }
}
