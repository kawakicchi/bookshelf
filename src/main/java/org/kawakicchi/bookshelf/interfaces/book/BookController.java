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
package org.kawakicchi.bookshelf.interfaces.book;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.kawakicchi.bookshelf.application.BookEntity;
import org.kawakicchi.bookshelf.application.BookshelfService;
import org.kawakicchi.bookshelf.application.ContentEntity;
import org.kawakicchi.bookshelf.application.PageEntity;
import org.kawakicchi.bookshelf.application.SeriesEntity;
import org.kawakicchi.bookshelf.application.ViewerService;
import org.kawakicchi.bookshelf.application.util.BookshelfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kawakicchi
 */
@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookshelfService bookshelfService;
	@Autowired
	private ViewerService viewerService;

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String book(final Model model) {
		setting(model);

		return "book/index";
	}

	@RequestMapping(value = { "/{bookSeq:^[0-9]+$}" }, method = RequestMethod.GET)
	public String get(@PathVariable("bookSeq") final Long bookSeq, final Model model) {
		setting(model);

		final BookEntity book = viewerService.getBook(bookSeq);
		model.addAttribute("book", book);

		final List<PageEntity> pageList = viewerService.getPageList(bookSeq);
		model.addAttribute("pageList", pageList);

		final List<List<PageEntity>> pageListList = new ArrayList<List<PageEntity>>();
		List<PageEntity> buf = new ArrayList<PageEntity>();
		for (int i = 0; i < pageList.size(); i++) {
			if (0 == i % 3) {
				pageListList.add(buf);
				buf = new ArrayList<PageEntity>();
			}
			buf.add(pageList.get(i));
		}
		model.addAttribute("pageListList", pageListList);

		return "book/book";
	}

	@RequestMapping(value = { "/store" }, method = RequestMethod.GET)
	public String prestore(final Model model) {
		setting(model);

		StoreForm form = new StoreForm();
		model.addAttribute("form", form);

		final List<SeriesEntity> seriesList = viewerService.getSeriesList();
		model.addAttribute("seriesList", seriesList);

		return "/book/store";
	}

	@RequestMapping(value = { "/store" }, method = RequestMethod.POST)
	public String store(@ModelAttribute StoreForm form, final Model model) {
		setting(model);

		if (BookshelfUtil.isBlank(form.getTitle()) || form.getFile().isEmpty()) {
			model.addAttribute("form", form);

			final List<SeriesEntity> seriesList = viewerService.getSeriesList();
			model.addAttribute("seriesList", seriesList);

			return "/book/store";
		}

		File tmpDir = new File("tmp");
		tmpDir.mkdirs();

		List<ContentEntity> files = new ArrayList<ContentEntity>();

		byte[] buf = new byte[1024];
		try (ZipInputStream zipStream = new ZipInputStream(form.getFile().getInputStream(), Charset.forName("MS932"))) {
			for (ZipEntry entry = zipStream.getNextEntry(); entry != null; entry = zipStream.getNextEntry()) {
				if (entry.isDirectory())
					continue;

				String id = BookshelfUtil.generateUUID();
				File tmpFile = Paths.get(tmpDir.getAbsolutePath(), id).toFile();
				OutputStream oStream = new FileOutputStream(tmpFile);

				int size = -1;
				while (0 <= (size = zipStream.read(buf, 0, 1024))) {
					oStream.write(buf, 0, size);
				}

				oStream.close();

				String[] ss = entry.getName().split("/");
				files.add(new ContentEntity(ss[ss.length - 1], tmpFile));
			}
		} catch (IOException ex) {

		}

		bookshelfService.createBook(form.getTitle(), form.getSeries(), files);

		return "/book/store";
	}

	private void setting(final Model model) {
		final String selectMenuBar = "book";
		model.addAttribute("selectMenuBar", selectMenuBar);

		// sidebar
		final List<BookEntity> bookList = viewerService.getBookList();
		model.addAttribute("bookList", bookList);

	}

	public static class StoreForm {
		private String title;
		private Long series;
		private MultipartFile file;

		/**
		 * title を取得する。
		 * 
		 * @return title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * title を設定する。
		 * 
		 * @param title title
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * series を取得する。
		 * 
		 * @return series
		 */
		public Long getSeries() {
			return series;
		}

		/**
		 * series を設定する。
		 * 
		 * @param series series
		 */
		public void setSeries(Long series) {
			this.series = series;
		}

		/**
		 * file を取得する。
		 * 
		 * @return file
		 */
		public MultipartFile getFile() {
			return file;
		}

		/**
		 * file を設定する。
		 * 
		 * @param file file
		 */
		public void setFile(MultipartFile file) {
			this.file = file;
		}

	}
}
