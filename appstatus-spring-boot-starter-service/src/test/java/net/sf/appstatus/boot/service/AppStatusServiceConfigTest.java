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
package net.sf.appstatus.boot.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.appstatus.core.services.IServiceManager;
import net.sf.appstatus.support.aop.AppStatusServiceInterceptor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppStatusServiceConfigTest {

    @Autowired
    private IServiceManager serviceManager;

    @Autowired
    private AppStatusServiceInterceptor interceptor;

    @Autowired
    private List<Advisor> advisors;

    @Test
    public void testNominal() {
        assertThat(serviceManager).isNotNull();

        Properties config = serviceManager.getConfiguration();
        assertThat(config.getProperty("services.minMaxDelay")).isEqualTo("123456789");

        assertThat(interceptor).isNotNull();

        assertThat(advisors).isNotNull()
                            .size()
                            .isEqualTo(2);
    }
}
