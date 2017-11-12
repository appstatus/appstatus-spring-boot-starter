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
package net.sf.appstatus.boot.web;

import java.util.List;

import net.sf.appstatus.core.AppStatus;
import net.sf.appstatus.core.AppStatusStatic;
import net.sf.appstatus.core.check.ICheck;
import net.sf.appstatus.core.property.IPropertyProvider;
import net.sf.appstatus.core.services.IServiceManager;
import net.sf.appstatus.support.spring.SpringObjectInstantiationListener;

/**
 * AppStatusBuilder.
 * 
 * @author Franck Stephanovitch
 *
 */
public class AppStatusBuilder {
    /** AppStatus instance. */
    private AppStatus instance = AppStatusStatic.getInstance();

    /**
     * Set service manager.
     * 
     * @param serviceManager
     * @return AppStatusBuilder this.
     */
    public AppStatusBuilder serviceManager(IServiceManager serviceManager) {
        instance.setServiceManager(serviceManager);
        return this;
    }

    /**
     * Set cherckers.
     * 
     * @param cherckers
     * @return AppStatusBuilder this.
     */
    public AppStatusBuilder checkers(List<ICheck> cherckers) {
        instance.setCheckers(cherckers);
        return this;
    }

    /**
     * Set propertyProviders.
     * 
     * @param propertyProviders
     * @return AppStatusBuilder this.
     */
    public AppStatusBuilder propertyProviders(List<IPropertyProvider> propertyProviders) {
        instance.setPropertyProviders(propertyProviders);
        return this;
    }

    /**
     * Set object instanciation listener.
     * 
     * @param objectInstanciationListener
     * @return AppStatusBuilder this.
     */
    public AppStatusBuilder objectInstanciationListener(SpringObjectInstantiationListener objectInstanciationListener) {
        instance.setObjectInstanciationListener(objectInstanciationListener);
        return this;
    }

    /**
     * Get AppStatus instance.
     * 
     * @return AppStatus instance
     */
    public AppStatus build() {
        return instance;
    }

}
