/*
 *  Copyright 2015 - 2016 John D. Ament
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.
 *
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package ws.ament.hammock.example.canonical;

import org.apache.cxf.jaxrs.swagger.Swagger2Feature;

import javax.enterprise.context.ApplicationScoped;

public class Resources {
    @ApplicationScoped
    public static class SwaggerComponent extends Swagger2Feature {
        public SwaggerComponent() {
            super.setContact("Hammock Project");
            super.setTitle("Microprofile Canonical Project");
        }
    }
}
