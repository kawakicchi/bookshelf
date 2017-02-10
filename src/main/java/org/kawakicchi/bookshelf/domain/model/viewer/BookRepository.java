/**
 * Copyright 2017 Azuki Framework.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kawakicchi.bookshelf.domain.model.viewer;

import java.util.List;

import org.kawakicchi.bookshelf.domain.shared.Sequence;

/**
 * @author kawakicchi
 */
public interface BookRepository {

	Series store(Series series);

	Book store(Book book);

	Page store(Page page);

	void append(Book book, Page page);

	void append(Series series, Book book);

	Series getSeries(Sequence<Series> seriesSeq);

	Book getBook(Sequence<Book> bookSeq);

	List<Series> findSeries();

	List<Book> findBook();

	List<Book> findBookBySeries(Series series);

	List<Page> findPageByBook(Book book);
}
