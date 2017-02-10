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
package org.kawakicchi.bookshelf.interfaces.page;

import java.io.InputStream;

import org.kawakicchi.bookshelf.domain.model.viewer.ContentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kawakicchi
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	private ContentStorage contentStorage;

	@ResponseBody
	@RequestMapping(value = "/{pageSeq:^[0-9]+$}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Resource file2(@PathVariable("pageSeq") Long pageSeq) {

		InputStream stream = contentStorage.get("photo/" + pageSeq);

		return new InputStreamResource(stream);
	}
}
