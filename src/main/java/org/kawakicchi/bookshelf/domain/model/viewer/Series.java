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
@Table(name = "td_series")
@Entity(immutable = true, naming = NamingType.SNAKE_LOWER_CASE)
public class Series implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -6598290834168162810L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final Sequence<Series> seriesSeq;

	private final SeriesName name;

	public Series(Sequence<Series> seriesSeq, SeriesName name) {
		super();
		this.seriesSeq = seriesSeq;
		this.name = name;
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
	 * name を取得する。
	 * 
	 * @return name
	 */
	public SeriesName getName() {
		return name;
	}

	public static Series create(final Series series, final BookRepository repository) {
		final Series result = repository.store(series);
		return result;
	}

	public static Series get(final Sequence<Series> seq, final BookRepository repository) {
		final Series result = repository.getSeries(seq);
		return result;
	}

	public boolean appendBook(final Book book, final BookRepository repository) {
		repository.append(this, book);
		return true;
	}

	public List<Book> getBookList(final BookRepository repository) {
		final List<Book> books = repository.findBookBySeries(this);
		return books;
	}

	public Page getTitlePage(final BookRepository repository) {
		Page page = new Page(new Sequence<Page>(1L), PageName.of(""), 100, 100);
		return page;
	}
}
