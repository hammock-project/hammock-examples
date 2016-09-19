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

import ws.ament.hammock.cqrs.domain.ChapterDefinition;
import ws.ament.hammock.cqrs.domain.CreateBook;
import ws.ament.hammock.cqrs.entity.BookEntity;
import ws.ament.hammock.cqrs.entity.ChapterEntity;
import ws.ament.hammock.cqrs.stereotype.Mapper;

import javax.inject.Inject;
import java.util.Date;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

@Mapper
public class NewBookFunction implements Function<CreateBook, BookEntity> {
    @Inject
    private Function<ChapterDefinition, ChapterEntity> chapterDefinitionChapterEntityFunction;
    @Override
    public BookEntity apply(CreateBook createBook) {

        BookEntity bookEntity = new BookEntity();

        bookEntity.setAuthorId(createBook.getAuthor());
        bookEntity.setCreateDate(new Date());
        bookEntity.setTitle(createBook.getTitle());
        bookEntity.setPageCount(createBook.getPageCount());

        Set<ChapterEntity> chapters = createBook.chapters().map(chapterDefinitionChapterEntityFunction).collect(toSet());
        chapters.forEach(c -> c.setBookEntity(bookEntity));
        bookEntity.setChapters(chapters);
        return bookEntity;
    }
}
