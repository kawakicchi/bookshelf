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
package org.kawakicchi.bookshelf.infrastructure.persistence.doma;

import java.util.List;

import org.kawakicchi.bookshelf.domain.model.viewer.Book;
import org.kawakicchi.bookshelf.domain.model.viewer.BookRepository;
import org.kawakicchi.bookshelf.domain.model.viewer.Page;
import org.kawakicchi.bookshelf.domain.model.viewer.Series;
import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.seasar.doma.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author kawakicchi
 */
@Component
public class DomaBookRepository implements BookRepository {

	@Autowired
	private BookDao bookDao;

	@Override
	public Series store(final Series series) {
		final Result<Series> result = bookDao.insert(series);
		return result.getEntity();
	}

	@Override
	public Book store(final Book book) {
		final Result<Book> result = bookDao.insert(book);
		return result.getEntity();
	}

	@Override
	public Page store(final Page page) {
		final Result<Page> result = bookDao.insert(page);
		return result.getEntity();
	}

	@Override
	public void append(final Series series, final Book book) {
		final Integer maxBookNo = bookDao.maxBookNo(series.getSeriesSeq());
		Integer no = Integer.valueOf(1);
		if (null != maxBookNo) {
			no = maxBookNo + 1;
		}

		final TrSeriesBook record = new TrSeriesBook(series.getSeriesSeq(), book.getBookSeq(), no);
		bookDao.insert(record);
	}

	@Override
	public void append(final Book book, final Page page) {
		final Integer maxPageNo = bookDao.maxPageNo(book.getBookSeq());
		Integer no = Integer.valueOf(1);
		if (null != maxPageNo) {
			no = maxPageNo + 1;
		}

		final TrBookPage record = new TrBookPage(book.getBookSeq(), page.getPageSeq(), no);
		bookDao.insert(record);
	}

	@Override
	public Series getSeries(final Sequence<Series> seriesSeq) {
		final Series series = bookDao.getSeriesBySeriesSeq(seriesSeq);
		return series;
	}

	@Override
	public Book getBook(final Sequence<Book> bookSeq) {
		final Book book = bookDao.getBookByBookSeq(bookSeq);
		return book;
	}

	@Override
	public List<Series> findSeries() {
		final List<Series> series = bookDao.selectSeries();
		return series;
	}

	@Override
	public List<Book> findBook() {
		final List<Book> books = bookDao.selectBook();
		return books;
	}

	@Override
	public List<Book> findBookBySeries(final Series series) {
		final List<Book> books = bookDao.selectBookBySeriesSeq(series.getSeriesSeq());
		return books;
	}

	@Override
	public List<Page> findPageByBook(final Book book) {
		final List<Page> pages = bookDao.selectPageByBookSeq(book.getBookSeq());
		return pages;
	}

}
