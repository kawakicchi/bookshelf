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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.kawakicchi.bookshelf.application.BookshelfService;
import org.kawakicchi.bookshelf.application.ContentEntity;
import org.kawakicchi.bookshelf.domain.model.viewer.Book;
import org.kawakicchi.bookshelf.domain.model.viewer.BookRepository;
import org.kawakicchi.bookshelf.domain.model.viewer.BookTitle;
import org.kawakicchi.bookshelf.domain.model.viewer.ContentStorage;
import org.kawakicchi.bookshelf.domain.model.viewer.Page;
import org.kawakicchi.bookshelf.domain.model.viewer.PageName;
import org.kawakicchi.bookshelf.domain.model.viewer.Series;
import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kawakicchi
 */
@Service
public class DefaultBookshelfService implements BookshelfService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ContentStorage contentStorage;

	@Override
	@Transactional
	public void createBook(final String title, final Long seriesSeq, final List<ContentEntity> contents) {

		Book book = new Book(null, BookTitle.of(title));
		book = Book.create(book, bookRepository);

		if (null != seriesSeq) {
			Series series = Series.get(new Sequence<Series>(seriesSeq), bookRepository);
			series.appendBook(book, bookRepository);
		}

		for (ContentEntity content : contents) {

			Page page = new Page(null, PageName.of(content.getName()), content.getWidth(), content.getHeight());
			page = Page.create(page, bookRepository);

			book.appendPage(page, bookRepository);

			try {
				contentStorage.store("photo/" + page.getPageSeq().getValue(), new FileInputStream(content.getFile()));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

}
