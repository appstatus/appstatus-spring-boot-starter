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
package net.sf.appstatus.boot.demo;

import org.hsqldb.util.DatabaseManagerSwing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * AppStatusSpringBootDemo launcher.
 * 
 * @author Franck Stephanovitch
 *
 */
@ComponentScan
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    /** Active or not hsqldb explorer */
    private static final boolean DEBUG_DB = false;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.boot.web.support.SpringBootServletInitializer#configure(
     * org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }

    /**
     * Application jar launcher.
     * 
     * @param args
     */
    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
        runDatabaseManager();
    }

    /**
     * Configure spring boot application.
     * 
     * @param builder
     *            application builder
     * @return SpringApplicationBuilder application builder
     */
    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        welcome();
        return builder.sources(Application.class)
                      .bannerMode(Banner.Mode.OFF);
    }

    /**
     * Run hsqldb explorer.
     */
    private static void runDatabaseManager() {
        if (DEBUG_DB) {
            System.setProperty("java.awt.headless", "false");
            DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:dbtest", "-noexit" });
        }
    }

    /**
     * Log start.
     */
    private static void welcome() {
        LOG.info("Starting application ...\n\n" //
                + " █████╗ ██████╗ ██████╗ ███████╗████████╗ █████╗ ████████╗██╗   ██╗███████╗\n" //
                + "██╔══██╗██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██║   ██║██╔════╝\n" //
                + "███████║██████╔╝██████╔╝███████╗   ██║   ███████║   ██║   ██║   ██║███████╗\n" //
                + "██╔══██║██╔═══╝ ██╔═══╝ ╚════██║   ██║   ██╔══██║   ██║   ██║   ██║╚════██║\n" //
                + "██║  ██║██║     ██║     ███████║   ██║   ██║  ██║   ██║   ╚██████╔╝███████║\n" //
                + "╚═╝  ╚═╝╚═╝     ╚═╝     ╚══════╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚══════╝\n");
    }

}
