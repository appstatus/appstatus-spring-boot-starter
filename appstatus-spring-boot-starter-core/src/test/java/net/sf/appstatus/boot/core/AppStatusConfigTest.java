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
package net.sf.appstatus.boot.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.appstatus.boot.core.api.ConfigBean;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppStatusConfigTest {

    @Autowired
    private ConfigBean configBean;

    @Test
    public void testNominal() {
        assertThat(configBean).isNotNull();
        assertThat(configBean.getAttributes()).isNotNull();
        assertThat(configBean.getAttributes()
                             .get("testAttribute")).isNotNull()
                                                   .isEqualTo("testAttribute");

        assertThat(configBean.getConfiguration()).isNotNull();
        assertThat(configBean.getConfiguration()
                             .get("testAttribute")).isNotNull()
                                                   .isEqualTo("testAttribute");
    }

}
