package com.gomilkyway.profile.adari.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    private PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @RequestMapping(path = "/get/allpages", produces = "application/json")
    public @ResponseBody Iterable<Page> getPublishedPages() {
        return pageService.getPublishedPages();
    }

}
