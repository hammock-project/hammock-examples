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

package ws.ament.hammock.cqrs.service;

import ws.ament.hammock.cqrs.domain.CreateBook;
import ws.ament.hammock.cqrs.entity.BookEntity;
import ws.ament.hammock.cqrs.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.function.Function;

@Service
public class CreateBookService {
    @Inject
    private EntityManager entityManager;
    @Inject
    private Function<CreateBook, BookEntity> createBookBookEntityFunction;

    public String createBook(CreateBook createBook) {
        BookEntity bookEntity = createBookBookEntityFunction.apply(createBook);
        BookEntity created = entityManager.merge(bookEntity);
        return created.getBookId();
    }
}
