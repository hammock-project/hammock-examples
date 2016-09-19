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


import ws.ament.hammock.cqrs.domain.UpdateBook;
import ws.ament.hammock.cqrs.entity.BookEntity;
import ws.ament.hammock.cqrs.mapping.Merger;
import ws.ament.hammock.cqrs.repository.BookSearchRepository;
import ws.ament.hammock.cqrs.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;

@Service
public class UpdateBookService {
    @Inject
    private Merger<BookEntity, UpdateBook> updateBookMerger;
    @Inject
    private EntityManager entityManager;
    @Inject
    private BookSearchRepository bookSearchRepository;

    public void updateBook(UpdateBook updateBook) {
        String bookId = updateBook.getBookId();
        BookEntity bookEntity = bookSearchRepository.findBookById(bookId)
                .orElseThrow(() -> new NotFoundException("Unable to find book with ID " + bookId));
        updateBookMerger.merge(bookEntity, updateBook);
        entityManager.merge(bookEntity);
    }
}
