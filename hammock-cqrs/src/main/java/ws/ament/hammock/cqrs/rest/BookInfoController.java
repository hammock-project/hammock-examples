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

import ws.ament.hammock.cqrs.domain.BookDetails;
import ws.ament.hammock.cqrs.domain.BookInfo;
import ws.ament.hammock.cqrs.entity.BookEntity;
import ws.ament.hammock.cqrs.repository.BookSearchRepository;
import ws.ament.hammock.cqrs.stereotype.RestController;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

@RestController
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookInfoController {
    @Inject
    private Function<BookEntity, BookDetails> bookDetailsFunction;
    @Inject
    private Function<BookEntity, BookInfo> bookInfoFunction;
    @Inject
    private BookSearchRepository bookSearchRepository;
    @GET
    public Response getAllBooks() {
        Collection<BookInfo> books = bookSearchRepository.findAll()
                .map(bookInfoFunction)
                .collect(toSet());
        return Response.ok(books).build();
    }

    @GET
    @Path("/{bookId}")
    public Response getBook(@PathParam("bookId") String bookId) {
        BookEntity bookEntity = bookSearchRepository.findBookById(bookId)
                .orElseThrow(() -> new NotFoundException("No book with ID "+bookId+" found"));
        BookDetails bookDetails = bookDetailsFunction.apply(bookEntity);
        return Response.ok(bookDetails).build();
    }
}
