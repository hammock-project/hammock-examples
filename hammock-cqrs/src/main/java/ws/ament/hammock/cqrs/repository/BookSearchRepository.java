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

package ws.ament.hammock.cqrs.repository;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import ws.ament.hammock.cqrs.entity.BookEntity;

import java.util.Optional;
import java.util.stream.Stream;

@Repository(forEntity = BookEntity.class)
public interface BookSearchRepository {
    @Query("select b from BookEntity b order by b.authorId")
    Stream<BookEntity> findAll();

    @Query("select b from BookEntity b where b.bookId = ?1")
    Optional<BookEntity> findBookById(String bookId);

    @Query("select b from BookEntity b where b.publishedDate is null and b.bookId = ?1")
    Optional<BookEntity> findUnpublishedBookById(String bookId);
}
