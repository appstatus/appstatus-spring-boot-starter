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
package net.sf.appstatus.boot.demo.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.glasnost.orika.MapperFacade;
import net.sf.appstatus.boot.demo.api.rest.SampleApi;
import net.sf.appstatus.boot.demo.common.ex.impl.ApiBadRequest;
import net.sf.appstatus.boot.demo.common.model.SampleObject;
import net.sf.appstatus.boot.demo.persistence.entity.SampleEntity;
import net.sf.appstatus.boot.demo.persistence.repository.SampleRepository;
import net.sf.appstatus.boot.demo.service.SampleService;

/**
 * Sample service.
 * 
 * @author Franck Stephanovitch
 *
 */
@Service
public class SampleServiceImpl implements SampleApi, SampleService {

    /** Mapper. */
    @Autowired
    private MapperFacade mapper;

    /** Repository. */
    @Autowired
    private SampleRepository repo;

    /*
     * (non-Javadoc)
     * 
     * @see net.sf.appstatus.boot.demo.api.rest.SampleApi#findObject(java.lang.Long)
     */
    @Override
    @Cacheable("sample_cache")
    @Transactional(readOnly = true)
    public SampleObject findObject(Long id) {
        if (id == null || id < 0L) {
            throw new ApiBadRequest("Missing param");
        }

        return mapper.map(repo.findOne(id), SampleObject.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.sf.appstatus.boot.demo.api.rest.SampleApi#findObjectNoCache(java.lang.
     * Long)
     */
    @Override
    @Transactional(readOnly = true)
    public SampleObject findObjectNoCache(Long id) {
        return findObject(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.sf.appstatus.boot.demo.service.SampleService#batchInsert(java.util.List)
     */
    @Override
    @Transactional
    public void batchInsert(List<SampleObject> elements) {
        if (CollectionUtils.isEmpty(elements)) {
            throw new ApiBadRequest("Missing param");
        }

        repo.save(mapper.mapAsList(elements, SampleEntity.class));
    }

}
