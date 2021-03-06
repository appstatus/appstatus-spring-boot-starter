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
package net.sf.appstatus.boot.logback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.appstatus.core.loggers.impl.LogbackLoggersManager;

/**
 * AppStatus logback configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
@Configuration
public class AppStatusLogbackConfig {

    /**
     * Create bean {@link LogbackLoggersManager}
     * 
     * @return LogbackLoggersManager bean
     */
    @Bean("net.sf.appstatus.core.loggers.impl.LogbackLoggersManager")
    public LogbackLoggersManager appstatusLogbackLoggersManager() {
        return new LogbackLoggersManager();
    }

}
