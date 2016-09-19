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

import ws.ament.hammock.cqrs.domain.CreateBook;
import ws.ament.hammock.cqrs.service.CreateBookService;
import ws.ament.hammock.cqrs.stereotype.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@RestController
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
public class CreateBookController {
    @Inject
    private CreateBookService createBookService;

    @POST
    public Response createBook(@Valid CreateBook createBook, @Context UriInfo uriInfo) {
        String bookId = createBookService.createBook(createBook);
        URI bookUri = uriInfo.getAbsolutePathBuilder().path(bookId).build();
        return Response.created(bookUri).build();
    }

}
