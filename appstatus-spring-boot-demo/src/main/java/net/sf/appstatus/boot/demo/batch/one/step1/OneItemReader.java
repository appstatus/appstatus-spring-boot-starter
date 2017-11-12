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

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import net.sf.appstatus.boot.demo.batch.StatusJobs;

/**
 * One job, one step, item reader.
 * 
 * @author Franck Stephanovitch
 * 
 */
@Component
public class OneItemReader implements ItemReader<Integer> {

    /** Reader source. */
    private static final AtomicInteger NB_ITEM = new AtomicInteger(100_000);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.batch.item.ItemReader#read()
     */
    @Override
    public Integer read() {
        int value = NB_ITEM.decrementAndGet();
        if (value == 0) {
            return null;
        }

        return value;
    }

    /**
     * retrieveInterstepData.
     * 
     * @param stepExecution
     *            to set
     */
    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        StatusJobs.getMonitor("One job", "element", stepExecution)
                  .beginTask("insert", "insert element", NB_ITEM.get());
    }

}
