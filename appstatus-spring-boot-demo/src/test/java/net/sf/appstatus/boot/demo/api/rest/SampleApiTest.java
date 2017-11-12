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
package net.sf.appstatus.boot.demo.api.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import net.sf.appstatus.boot.demo.Application;
import net.sf.appstatus.boot.demo.common.Jsons;
import net.sf.appstatus.boot.demo.common.ex.AbstractApiException;
import net.sf.appstatus.boot.demo.common.ex.impl.ApiBadRequest;
import net.sf.appstatus.boot.demo.common.model.SampleObject;
import net.sf.appstatus.boot.demo.service.SampleService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
public class SampleApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SampleApi api;

    @Autowired
    private SampleService service;

    @Test
    public void testNominal() {
        assertThat(api).isNotNull();

        SampleObject expected = new SampleObject();
        expected.setId(1L);
        expected.setText("text 1");

        assertThat(api.findObject(1L)).isEqualToComparingFieldByField(expected);
        assertThat(api.findObjectNoCache(1L)).isEqualToComparingFieldByField(expected);

        String expectedJson = Jsons.toJson(expected);

        String bodyWithCache = this.restTemplate.getForObject("/api/sample/1", String.class);
        assertThat(bodyWithCache).isEqualTo(expectedJson);

        String bodyNoCache = this.restTemplate.getForObject("/api/sample/nocache/1", String.class);
        assertThat(bodyNoCache).isEqualTo(expectedJson);

        SampleObject toSave = new SampleObject();
        toSave.setText("text test");

        service.batchInsert(Collections.singletonList(toSave));

    }

    @Test
    public void testNoContent() {
        ResponseEntity<String> noContent = this.restTemplate.getForEntity("/api/sample/0", String.class);
        assertThat(noContent.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

    @Test
    public void testBadRequest() {
        ResponseEntity<String> noContent = this.restTemplate.getForEntity("/api/sample/-1", String.class);
        assertThat(noContent.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        AbstractApiException ex = new ApiBadRequest("Missing param");
        assertThat(noContent.getBody()).isEqualTo(Jsons.toJson(ex.toError()));
    }

    @Test(expected = ApiBadRequest.class)
    public void testMissingParam() {
        api.findObject(null);
    }

    @Test(expected = ApiBadRequest.class)
    public void testMissingParamInsert() {
        service.batchInsert(Collections.emptyList());
    }

}
