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

import java.io.Serializable;
import java.util.List;

import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * @author kawakicchi
 */
@Table(name = "td_book")
@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
public class Book implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -4576948426895036879L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Sequence<Book> bookSeq;

	private final BookTitle title;

	public Book(Sequence<Book> bookSeq, BookTitle title) {
		super();
		this.bookSeq = bookSeq;
		this.title = title;
	}

	/**
	 * bookSeq を取得する。
	 * 
	 * @return bookSeq
	 */
	public Sequence<Book> getBookSeq() {
		return bookSeq;
	}

	/**
	 * title を取得する。
	 * 
	 * @return title
	 */
	public BookTitle getTitle() {
		return title;
	}

	public static Book create(final Book book, final BookRepository repository) {
		final Book result = repository.store(book);
		return result;
	}

	public boolean appendPage(final Page page, final BookRepository repository) {
		repository.append(this, page);
		return true;
	}

	public List<Page> getPageList(final BookRepository repository) {
		final List<Page> pages = repository.findPageByBook(this);
		return pages;
	}
}
