/*
 * Copyright 2023 Ari Adari
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

package com.gomilkyway.profile.adari.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    private PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8082", "http://localhost:8086"})
    @GetMapping(path = "/get/allpages", produces = "application/json")
    public @ResponseBody Iterable<Page> getPublishedPages() {
        return pageService.getPublishedPages();
    }

}
