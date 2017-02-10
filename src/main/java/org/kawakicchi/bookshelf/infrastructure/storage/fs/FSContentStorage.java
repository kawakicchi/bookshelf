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
package org.kawakicchi.bookshelf.infrastructure.storage.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.kawakicchi.bookshelf.domain.model.viewer.ContentStorage;
import org.springframework.stereotype.Component;

/**
 * @author kawakicchi
 */
@Component
public class FSContentStorage implements ContentStorage {

	private static final File BASE_DIR = new File("C:\\tmp\\test");

	public static void main(final String[] args) {
		String plan = "";

		String[] ss = plan.split("/");
		for (String s : ss) {
			System.out.println("-" + s);
		}
	}

	public void store(final String key, final InputStream stream) {
		String[] s = key.split("/");
		Path path = Paths.get(BASE_DIR.getAbsolutePath(), s);
		if (2 <= s.length) {
			File parent = path.getParent().toFile();
			parent.mkdirs();
		}

		OutputStream out = null;
		try {
			out = new FileOutputStream(path.toFile());

			byte[] buf = new byte[1024];
			int size = -1;
			while (-1 != (size = stream.read(buf, 0, 1024))) {
				out.write(buf, 0, size);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException ex) {

				}
			}
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException ex) {

				}
			}
		}
	}

	public InputStream get(final String key) {
		String[] s = key.split("/");
		Path path = Paths.get(BASE_DIR.getAbsolutePath(), s);

		InputStream stream = null;
		try {
			stream = new FileInputStream(path.toFile());
		} catch (IOException ex) {

		}

		return stream;
	}
}
