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

import org.apache.deltaspike.core.api.config.ConfigProperty;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@RequestScoped
public class CORSFilter implements ContainerResponseFilter {
    @Inject
    @ConfigProperty(name = "cors.enabled")
    private boolean enabled;
    @Inject
    @ConfigProperty(name = "cors.origin")
    private String origin;
    @Inject
    @ConfigProperty(name = "cors.headers")
    private String headers;
    @Inject
    @ConfigProperty(name = "cors.credentials")
    private String credentials;
    @Inject
    @ConfigProperty(name = "cors.methods")
    private String methods;
    @Inject
    @ConfigProperty(name = "cors.maxAge")
    private String maxAge;

    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext responseContext) throws IOException {
        if(enabled) {
            MultivaluedMap<String, Object> headers = responseContext.getHeaders();
            headers.add("Access-Control-Allow-Origin", origin);
            headers.add("Access-Control-Allow-Headers", this.headers);
            headers.add("Access-Control-Allow-Credentials", credentials);
            headers.add("Access-Control-Allow-Methods", methods);
            headers.add("Access-Control-Max-Age", maxAge);
        }
    }

}