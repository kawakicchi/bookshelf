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
import org.kawakicchi.bookshelf.domain.model.viewer.Page;
import org.kawakicchi.bookshelf.domain.model.viewer.Series;
import org.kawakicchi.bookshelf.domain.shared.Sequence;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Result;

/**
 * @author kawakicchi
 */
@Dao
@ConfigAutowireable
public interface BookDao {

	@Insert
	Result<Series> insert(Series series);

	@Insert
	Result<Book> insert(Book book);

	@Insert
	Result<Page> insert(Page page);

	@Insert
	Result<TrSeriesBook> insert(TrSeriesBook record);

	@Insert
	Result<TrBookPage> insert(TrBookPage record);

	@Select
	Integer maxBookNo(Sequence<Series> seriesSeq);

	@Select
	Integer maxPageNo(Sequence<Book> bookSeq);

	@Select
	Series getSeriesBySeriesSeq(Sequence<Series> seriesSeq);

	@Select
	Book getBookByBookSeq(Sequence<Book> bookSeq);

	@Select
	List<Series> selectSeries();

	@Select
	List<Book> selectBook();

	@Select
	List<Book> selectBookBySeriesSeq(Sequence<Series> seriesSeq);

	@Select
	List<Page> selectPageByBookSeq(Sequence<Book> bookSeq);
}
