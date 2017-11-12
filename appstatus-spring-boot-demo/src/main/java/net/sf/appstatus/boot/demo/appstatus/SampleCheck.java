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
package net.sf.appstatus.boot.demo.appstatus;

import java.util.Locale;

import net.sf.appstatus.boot.web.checker.AppStatusCheck;
import net.sf.appstatus.core.check.AbstractCheck;
import net.sf.appstatus.core.check.ICheckResult;

/**
 * Sample checker.
 * 
 * @author Franck Stephanovitch
 *
 */
@AppStatusCheck
public class SampleCheck extends AbstractCheck {

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.appstatus.core.check.AbstractCheck#checkStatus(java.util.Locale)
     */
    @Override
    public ICheckResult checkStatus(Locale locale) {
        return result(this).description("Dummy description")
                           .code(ICheckResult.OK)
                           .build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.appstatus.core.check.ICheck#getGroup()
     */
    @Override
    public String getGroup() {
        return "Sample";
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.appstatus.core.check.ICheck#getName()
     */
    @Override
    public String getName() {
        return "SampleCheck";
    }

}
