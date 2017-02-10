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
import org.seasar.doma.Entity;

/**
 * @author kawakicchi
 */
@Entity(immutable = true)
public class Viewer {

	public Viewer() {

	}

	public static Viewer getViewer() {
		final Viewer viewer = new Viewer();
		return viewer;
	}

	public Series getSeries(final Sequence<Series> seq, final BookRepository repository) {
		final Series series = repository.getSeries(seq);
		return series;
	}

	public Book getBook(final Sequence<Book> seq, final BookRepository repository) {
		final Book book = repository.getBook(seq);
		return book;
	}

	public List<Series> getSeriesList(final BookRepository repository) {
		final List<Series> series = repository.findSeries();
		return series;
	}

	public List<Book> getBookList(final BookRepository repository) {
		final List<Book> books = repository.findBook();
		return books;
	}

}
