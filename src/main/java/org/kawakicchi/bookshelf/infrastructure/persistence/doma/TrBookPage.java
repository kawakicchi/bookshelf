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

import org.kawakicchi.bookshelf.domain.model.viewer.Book;
import org.kawakicchi.bookshelf.domain.model.viewer.Page;
import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * @author kawakicchi
 */
@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
public class TrBookPage {

	private final Sequence<Book> bookSeq;
	private final Sequence<Page> pageSeq;
	private final Integer no;

	public TrBookPage(Sequence<Book> bookSeq, Sequence<Page> pageSeq, Integer no) {
		super();
		this.bookSeq = bookSeq;
		this.pageSeq = pageSeq;
		this.no = no;
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
	 * pageSeq を取得する。
	 * 
	 * @return pageSeq
	 */
	public Sequence<Page> getPageSeq() {
		return pageSeq;
	}

	/**
	 * no を取得する。
	 * 
	 * @return no
	 */
	public Integer getNo() {
		return no;
	}

}
