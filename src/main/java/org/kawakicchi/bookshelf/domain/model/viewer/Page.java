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
@Table(name = "td_page")
@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Sequence<Page> pageSeq;

	private final PageName name;

	private final int width;

	private final int height;

	public Page(Sequence<Page> pageSeq, PageName name, int width, int height) {
		super();
		this.pageSeq = pageSeq;
		this.name = name;
		this.width = width;
		this.height = height;
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
	 * name を取得する。
	 * 
	 * @return name
	 */
	public PageName getName() {
		return name;
	}

	/**
	 * width を取得する。
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * height を取得する。
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	public static Page create(final Page page, final BookRepository repository) {
		final Page result = repository.store(page);
		return result;
	}
}
