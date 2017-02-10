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

import org.kawakicchi.bookshelf.domain.model.viewer.Page;

/**
 * @author kawakicchi
 */
public class PageEntity {

	private Long seq;
	private String name;

	public PageEntity(Long seq, String name) {
		super();
		this.seq = seq;
		this.name = name;
	}

	public PageEntity(Page page) {
		this.seq = page.getPageSeq().getValue();
		this.name = page.getName().getValue();
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
	 * name を取得する。
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

}
