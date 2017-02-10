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
package org.kawakicchi.bookshelf.application.internal;

import java.util.ArrayList;
import java.util.List;

import org.kawakicchi.bookshelf.application.BookEntity;
import org.kawakicchi.bookshelf.application.PageEntity;
import org.kawakicchi.bookshelf.application.SeriesEntity;
import org.kawakicchi.bookshelf.application.ViewerService;
import org.kawakicchi.bookshelf.domain.model.viewer.Book;
import org.kawakicchi.bookshelf.domain.model.viewer.BookRepository;
import org.kawakicchi.bookshelf.domain.model.viewer.Page;
import org.kawakicchi.bookshelf.domain.model.viewer.Series;
import org.kawakicchi.bookshelf.domain.model.viewer.Viewer;
import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kawakicchi
 */
@Service
public class DefaultViewerService implements ViewerService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public SeriesEntity getSeries(final Long seriesSeq) {
		final Viewer viewer = Viewer.getViewer();

		final Series series = viewer.getSeries(new Sequence<Series>(seriesSeq), bookRepository);

		final SeriesEntity result = new SeriesEntity(series);

		Page page = series.getTitlePage(bookRepository);
		if (null != page) {
			result.setTitlePageSeq(page.getPageSeq().getValue());
		}
		return result;
	}

	@Override
	public BookEntity getBook(final Long bookSeq) {
		final Viewer viewer = Viewer.getViewer();

		final Book book = viewer.getBook(new Sequence<Book>(bookSeq), bookRepository);

		final BookEntity result = new BookEntity(book);
		return result;
	}

	@Override
	public List<SeriesEntity> getSeriesList() {
		final Viewer viewer = Viewer.getViewer();

		final List<Series> seriesList = viewer.getSeriesList(bookRepository);

		final List<SeriesEntity> result = new ArrayList<SeriesEntity>();
		seriesList.forEach(series -> {
			SeriesEntity e = new SeriesEntity(series);
			final Page page = series.getTitlePage(bookRepository);
			if (null != page) {
				e.setTitlePageSeq(page.getPageSeq().getValue());
			}
			result.add(e);
		});
		return result;
	}

	@Override
	public List<BookEntity> getBookList() {
		final Viewer viewer = Viewer.getViewer();

		final List<Book> bookList = viewer.getBookList(bookRepository);

		final List<BookEntity> result = new ArrayList<BookEntity>();
		bookList.forEach(book -> result.add(new BookEntity(book)));
		return result;
	}

	@Override
	public List<BookEntity> getBookList(final Long seriesSeq) {
		final Viewer viewer = Viewer.getViewer();

		final Series series = viewer.getSeries(new Sequence<Series>(seriesSeq), bookRepository);

		final List<Book> bookList = series.getBookList(bookRepository);

		final List<BookEntity> result = new ArrayList<BookEntity>();
		bookList.forEach(book -> result.add(new BookEntity(book)));
		return result;
	}

	@Override
	public List<PageEntity> getPageList(Long bookSeq) {
		final Viewer viewer = Viewer.getViewer();

		final Book book = viewer.getBook(new Sequence<Book>(bookSeq), bookRepository);

		final List<Page> pageList = book.getPageList(bookRepository);

		final List<PageEntity> result = new ArrayList<PageEntity>();
		pageList.forEach(page -> result.add(new PageEntity(page)));
		return result;
	}

}
