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
import org.kawakicchi.bookshelf.domain.model.viewer.Series;
import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 * @author kawakicchi
 */
@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
public class TrSeriesBook {

	private final Sequence<Series> seriesSeq;
	private final Sequence<Book> bookSeq;
	private final Integer no;

	public TrSeriesBook(Sequence<Series> seriesSeq, Sequence<Book> bookSeq, Integer no) {
		super();
		this.seriesSeq = seriesSeq;
		this.bookSeq = bookSeq;
		this.no = no;
	}

	/**
	 * seriesSeq を取得する。
	 * 
	 * @return seriesSeq
	 */
	public Sequence<Series> getSeriesSeq() {
		return seriesSeq;
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
	 * no を取得する。
	 * 
	 * @return no
	 */
	public Integer getNo() {
		return no;
	}

}
