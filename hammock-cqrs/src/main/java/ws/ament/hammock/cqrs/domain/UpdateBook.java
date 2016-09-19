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

package ws.ament.hammock.cqrs.domain;

import org.apache.johnzon.mapper.JohnzonProperty;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

public class UpdateBook {
    private String bookId;
    @JohnzonProperty("author")
    private String author;
    @JohnzonProperty("title")
    private String title;
    @JohnzonProperty("pageCount")
    private int pageCount;
    @JohnzonProperty("chapters")
    private Set<ChapterDefinition> chapters;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setChapters(Set<ChapterDefinition> chapters) {
        this.chapters = chapters;
    }

    public Set<ChapterDefinition> getChapters() {
        return Collections.unmodifiableSet(chapters);
    }

    public Stream<ChapterDefinition> chapters() {
        return getChapters().stream();
    }
}
