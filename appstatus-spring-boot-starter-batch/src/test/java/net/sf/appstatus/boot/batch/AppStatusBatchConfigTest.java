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
package net.sf.appstatus.boot.batch;

import static net.sf.appstatus.boot.batch.AppStatusBatchConstants.DEFAULT_LOG_INTERVAL;
import static net.sf.appstatus.boot.batch.AppStatusBatchConstants.DEFAULT_TABLE_NAME;
import static net.sf.appstatus.boot.batch.AppStatusBatchConstants.DEFAULT_ZOMBIE_INTERVAL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.appstatus.batch.jdbc.BatchDao;
import net.sf.appstatus.batch.jdbc.JdbcBatchManager;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@SpringBootTest
public class AppStatusBatchConfigTest {

    @Autowired
    private JdbcBatchManager batchManager;

    @Autowired
    private BatchDao batchDao;

    @Test
    public void testNominal() {
        assertThat(batchDao).isNotNull();
        assertThat(batchDao).hasFieldOrPropertyWithValue("tableName", DEFAULT_TABLE_NAME);
        assertThat(batchDao).extracting("jdbcTemplate")
                            .isNotNull();

        assertThat(batchManager).isNotNull();
        assertThat(batchManager).hasFieldOrPropertyWithValue("logInterval", DEFAULT_LOG_INTERVAL);
        assertThat(batchManager).hasFieldOrPropertyWithValue("zombieInterval", DEFAULT_ZOMBIE_INTERVAL);
        assertThat(batchManager).extracting("batchDao")
                                .isNotNull();
    }

}
