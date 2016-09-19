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

public class BookInfo {
    @JohnzonProperty("bookId")
    private String bookId;
    @JohnzonProperty("author")
    private String author;
    @JohnzonProperty("title")
    private String title;
    @JohnzonProperty("pageCount")
    private int pageCount;

    public BookInfo(String bookId, String author, String title, int pageCount) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.pageCount = pageCount;
    }

    public String getBookId() {
        return bookId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPageCount() {
        return pageCount;
    }
}
