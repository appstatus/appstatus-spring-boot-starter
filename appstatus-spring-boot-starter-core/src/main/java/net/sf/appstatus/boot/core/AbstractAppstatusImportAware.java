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

import java.util.Map;

import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Abstract EnableAppStatus* configuration.
 * 
 * @author Franck Stephanovitch
 *
 */
public abstract class AbstractAppstatusImportAware implements ImportAware {

    private Class<?> starterClass;

    /**
     * Constructor.
     * 
     * @param starterClass
     *            annotation class
     */
    public AbstractAppstatusImportAware(Class<?> starterClass) {
        this.starterClass = starterClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.context.annotation.ImportAware#setImportMetadata(org.
     * springframework.core.type.AnnotationMetadata)
     */
    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        Map<String, Object> map = annotationMetadata.getAnnotationAttributes(starterClass.getName());
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(map);

        initialize(attributes, new AppstatusConfigBuilder(attributes));
    }

    protected abstract void initialize(AnnotationAttributes attributes, AppstatusConfigBuilder configBuilder);

}
