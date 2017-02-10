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
package org.kawakicchi.bookshelf.interfaces.series;

import java.util.ArrayList;
import java.util.List;

import org.kawakicchi.bookshelf.application.SeriesEntity;
import org.kawakicchi.bookshelf.application.ViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kawakicchi
 */
@Controller
@RequestMapping("/series")
public class SeriesController {

	@Autowired
	private ViewerService viewerService;

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String book(Model model) {
		setting(model);

		final List<SeriesEntity> seriesList = (List<SeriesEntity>) model.asMap().get("seriesList");

		final List<List<SeriesEntity>> seriesListList = new ArrayList<List<SeriesEntity>>();
		List<SeriesEntity> buf = new ArrayList<SeriesEntity>();
		for (int i = 0; i < seriesList.size(); i++) {
			if (0 == i % 3) {
				seriesListList.add(buf);
				buf = new ArrayList<SeriesEntity>();
			}
			buf.add(seriesList.get(i));
		}
		model.addAttribute("seriesListList", seriesListList);

		return "series/index";
	}

	@RequestMapping(value = { "/{seriesSeq:^[0-9]+$}" }, method = RequestMethod.GET)
	public String get(@PathVariable("seriesSeq") Long seriesSeq, Model model) {
		setting(model);

		final SeriesEntity series = viewerService.getSeries(seriesSeq);
		model.addAttribute("series", series);

		return "series/series";
	}

	private void setting(final Model model) {
		final String selectMenuBar = "series";
		model.addAttribute("selectMenuBar", selectMenuBar);

		// sidebar
		final List<SeriesEntity> seriesList = viewerService.getSeriesList();
		model.addAttribute("seriesList", seriesList);

	}

}
