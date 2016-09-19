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

package ws.ament.hammock.cqrs.mapping;

import ws.ament.hammock.cqrs.domain.BookPublication;
import ws.ament.hammock.cqrs.entity.BookEntity;
import ws.ament.hammock.cqrs.stereotype.Mapper;

import java.util.function.Function;

@Mapper
public class BookPublicationFunction implements Function<BookEntity, BookPublication> {
    private static final BookPublication UNPUBLISHED = new BookPublication(false, null);

    @Override
    public BookPublication apply(BookEntity bookEntity) {
        if(bookEntity.getPublishedDate() == null) {
            return UNPUBLISHED;
        }
        else {
            return new BookPublication(true, bookEntity.getPublishedDate());
        }
    }
}
