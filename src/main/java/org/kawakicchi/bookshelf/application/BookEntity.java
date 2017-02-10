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
package org.kawakicchi.bookshelf.application;

import org.kawakicchi.bookshelf.domain.model.viewer.Book;

/**
 * @author kawakicchi
 */
public class BookEntity {

	private Long seq;
	private String title;

	public BookEntity(Long seq, String title) {
		super();
		this.seq = seq;
		this.title = title;
	}

	public BookEntity(Book book) {
		this.seq = book.getBookSeq().getValue();
		this.title = book.getTitle().getValue();
	}

	/**
	 * seq を取得する。
	 * 
	 * @return seq
	 */
	public Long getSeq() {
		return seq;
	}

	/**
	 * title を取得する。
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

}
