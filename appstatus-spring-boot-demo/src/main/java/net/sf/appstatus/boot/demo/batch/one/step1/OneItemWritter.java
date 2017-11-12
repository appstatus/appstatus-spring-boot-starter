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
package net.sf.appstatus.boot.demo.batch.one.step1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.appstatus.boot.demo.batch.StatusJobs;
import net.sf.appstatus.boot.demo.common.model.SampleObject;
import net.sf.appstatus.boot.demo.service.SampleService;

/**
 * One job, one step, item writter.
 * 
 * @author Franck Stephanovitch
 *
 */
@Component
public class OneItemWritter implements ItemWriter<SampleObject> {

    /** Service. */
    @Autowired
    private SampleService service;

    /** StepExecution. */
    private StepExecution stepExecution;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
     */
    @Override
    public void write(List<? extends SampleObject> items) {
        service.batchInsert(items.stream()
                                 .map(SampleObject.class::cast)
                                 .collect(Collectors.toList()));
        StatusJobs.getMonitor("One job", "element", stepExecution)
                  .worked(items.size());
    }

    /**
     * retrieveInterstepData.
     * 
     * @param stepExecution
     *            to set
     */
    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

}
