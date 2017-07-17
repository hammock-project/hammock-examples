/*
 *  Copyright 2015 - 2017 John D. Ament
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

package ws.ament.hammock.keycloak.consumer;

import org.keycloak.KeycloakPrincipal;
import ws.ament.hammock.Bootstrap;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/api/loggedin")
@RequestScoped
public class LoggedInResource {
    @Context
    private SecurityContext securityContext;
    @GET
    public Response getResponse() {
        KeycloakPrincipal principal = KeycloakPrincipal.class.cast(securityContext.getUserPrincipal());
        return Response.ok(principal.getKeycloakSecurityContext().getToken().getPreferredUsername()).build();
    }

    public static void main(String...args) {
        Bootstrap.main(args);
    }
}
