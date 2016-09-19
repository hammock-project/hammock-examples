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

import ws.ament.hammock.cqrs.domain.BookDetails;
import ws.ament.hammock.cqrs.domain.BookInfo;
import ws.ament.hammock.cqrs.domain.BookPublication;
import ws.ament.hammock.cqrs.domain.ChapterDefinition;
import ws.ament.hammock.cqrs.entity.BookEntity;
import ws.ament.hammock.cqrs.entity.ChapterEntity;
import ws.ament.hammock.cqrs.stereotype.Mapper;

import javax.inject.Inject;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toSet;

@Mapper
public class BookDetailsFunction implements Function<BookEntity, BookDetails> {
    @Inject
    private Function<BookEntity, BookInfo> bookInfoFunction;

    @Inject
    private Function<BookEntity, BookPublication> bookPublicationFunction;

    @Inject
    private Function<ChapterEntity, ChapterDefinition> chapterFunction;


    @Override
    public BookDetails apply(BookEntity bookEntity) {
        BookInfo bookInfo = bookInfoFunction.apply(bookEntity);
        BookPublication bookPublication = bookPublicationFunction.apply(bookEntity);
        Set<ChapterDefinition> chapters = bookEntity.getChapters().stream().map(chapterFunction).collect(toSet());
        return new BookDetails(bookInfo, bookPublication, chapters);
    }
}
