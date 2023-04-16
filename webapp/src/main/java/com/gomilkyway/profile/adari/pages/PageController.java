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

    @CrossOrigin(origins = "http://localhost:8080, http://localhost:8082")
    @GetMapping(path = "/get/allpages", produces = "application/json")
    public @ResponseBody Iterable<Page> getPublishedPages() {
        return pageService.getPublishedPages();
    }

}
