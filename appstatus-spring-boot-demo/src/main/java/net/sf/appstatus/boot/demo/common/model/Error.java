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
package net.sf.appstatus.boot.demo.common.model;

/**
 * Error model.
 * 
 * @author Franck Stephanovitch
 * 
 */
public class Error {
    /** Http status code. */
    private String status;
    /** Error message. */
    private String message;

    /**
     * Error builder
     * 
     * @author Franck Stephanovitch
     *
     */
    public static class Builder {
        /** Http status code. */
        private String status;
        /** Error message. */
        private String message;

        /**
         * Set status code.
         * 
         * @param status
         *            status to set
         * @return Builder
         */
        public Builder status(String status) {
            this.status = status;
            return this;
        }

        /**
         * Set message exception.
         * 
         * @param message
         *            message to set
         * @return Builder
         */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * Build Error object from this builder.
         * 
         * @return Error
         */
        public Error build() {
            return new Error(this);
        }
    }

    /**
     * Constructor.
     * 
     * @param builder
     */
    public Error(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
