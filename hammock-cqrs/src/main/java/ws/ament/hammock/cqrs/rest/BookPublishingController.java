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

package ws.ament.hammock.cqrs.rest;

import ws.ament.hammock.cqrs.service.UnpublishedBookService;
import ws.ament.hammock.cqrs.stereotype.RestController;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@Path("/books/{bookId}/publication")
@Consumes(MediaType.APPLICATION_JSON)
public class BookPublishingController {
    @Inject
    private UnpublishedBookService unpublishedBookService;

    @POST
    public Response publishBook(@PathParam("bookId") String bookId) {
        unpublishedBookService.publishBook(bookId);
        return Response.accepted().build();
    }
}
