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

import java.io.File;

/**
 * @author kawakicchi
 */
public class ContentEntity {
	private String name;
	private File file;
	private int width;
	private int height;

	public ContentEntity(String name, File file, int width, int height) {
		super();
		this.name = name;
		this.file = file;
		this.width = width;
		this.height = height;
	}

	/**
	 * name を取得する。
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * file を取得する。
	 * 
	 * @return file
	 */
	public File getFile() {
		return file;
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

}