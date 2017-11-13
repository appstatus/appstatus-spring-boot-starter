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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import net.sf.appstatus.web.IPage;
import net.sf.appstatus.web.StatusServlet;

/**
 * AppStatus servle builder.
 * 
 * @author Franck Stephanovitch
 *
 */
public class AppStatusServletBuilder {
    /** Servlet urlMappings. */
    private List<String> urlMappings;

    /** Servlet customPages. */
    private String customPages = StringUtils.EMPTY;

    /**
     * Add url mappings.
     * 
     * @param urlMappings
     *            to set
     * @return AppStatusServletBuilder this
     */
    public AppStatusServletBuilder urlMappings(String[] urlMappings) {
        this.urlMappings = Arrays.asList(urlMappings);
        return this;
    }

    /**
     * Add custom pages.
     * 
     * @param customPages
     *            to set
     * @return AppStatusServletBuilder this
     */
    public AppStatusServletBuilder customPages(List<IPage> customPages) {
        if (CollectionUtils.isNotEmpty(customPages)) {
            this.customPages = customPages.stream()
                                          .map((IPage p) -> p.getClass()
                                                             .getSimpleName())
                                          .map(StringUtils::uncapitalize)
                                          .collect(Collectors.joining(", "));
        }

        return this;
    }

    /**
     * Spring Servlet Registration bean for StatusServlet.
     * 
     * @return ServletRegistrationBean
     */
    public ServletRegistrationBean servletRegistrationBean() {
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("bean", AppStatusWebConstants.BEAN_NAME);
        initParameters.put("custom-pages", customPages);

        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new StatusServlet());
        srb.setUrlMappings(urlMappings);
        srb.setInitParameters(initParameters);

        return srb;
    }

}
