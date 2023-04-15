package com.gomilkyway.profile.adari.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
public class PageController {

    private PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    /* USER FRONT */

    @RequestMapping(path = "/get/allpages", produces = "application/json")
    public @ResponseBody Iterable<Page> getPublishedPages() {
        return pageService.getPublishedPages();
    }


    /* ADMIN BACK */
    @PostMapping(path = "/admin/savepage")
    public String savePage(@Valid Page page, BindingResult bindingResult, Model model) {
        
        return "redirect:admin/pages";
    }

}
