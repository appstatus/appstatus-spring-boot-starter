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
package net.sf.appstatus.boot.demo.batch;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;

import net.sf.appstatus.core.AppStatusStatic;
import net.sf.appstatus.core.batch.IBatchProgressMonitor;

/**
 * Status jobs utils.
 * 
 * @author Franck Stephanovitch
 *
 */
public final class StatusJobs {
    /** Job UUID context key. */
    private static final String JOB_UUID_KEY = "jobUUID";

    /**
     * Constructor.
     */
    private StatusJobs() {
        // Nothing to do
    }

    /**
     * Get appstatus monitor.
     * 
     * @param name
     *            monitor name
     * @param group
     *            monitor group
     * @param stepContext
     *            step context
     * @return IBatchProgressMonitor monitor
     */
    public static IBatchProgressMonitor getMonitor(String name, String group, StepExecution stepContext) {
        return getMonitor(name, group, stepContext.getJobExecution()
                                                  .getExecutionContext());
    }

    /**
     * Get appstatus monitor.
     * 
     * @param name
     *            monitor name
     * @param group
     *            monitor group
     * @param executionContext
     *            execution context
     * @return IBatchProgressMonitor monitor
     */
    public static IBatchProgressMonitor getMonitor(String name, String group, ExecutionContext executionContext) {
        return AppStatusStatic.getInstance()
                              .getBatchProgressMonitor(name, group, executionContext.getString(JOB_UUID_KEY));
    }

    /**
     * Get appstatus monitor.
     * 
     * @param name
     *            monitor name
     * @param group
     *            monitor group
     * @param uuid
     *            job uuid
     * @return IBatchProgressMonitor monitor
     */
    public static IBatchProgressMonitor getMonitor(String name, String group, String uuid) {
        return AppStatusStatic.getInstance()
                              .getBatchProgressMonitor(name, group, uuid);
    }

}
