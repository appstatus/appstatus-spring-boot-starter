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
package net.sf.appstatus.boot.web.provider;

import net.sf.appstatus.core.property.impl.WarMavenVersionProvider;

/**
 * Create spring bean of {@link WarMavenVersionProvider}. <br />
 * Doesn't work if application is run with "java -jar"
 * 
 * @author Franck Stephanovitch
 *
 */
@AppStatusPropertyProvider
public class SpringifyMavenPropertyProvider extends WarMavenVersionProvider {

}
