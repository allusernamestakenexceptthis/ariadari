package com.gomilkyway.profile.adari.admin.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.gomilkyway.profile.adari.pages.Page;
import com.gomilkyway.profile.adari.pages.PageService;

import jakarta.validation.Valid;

@Controller
public class AdminPageController {
    private PageService pageService;

    public AdminPageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(path = "/admin/pages")
    public String getPublishedPages(Model model)  {
        model.addAttribute("pages", pageService.getPublishedPages());
        return "admin/pages";
    }

    @GetMapping(path = "/admin/pages/new")
    public String newPage(Model model)  {
        model.addAttribute("page", new Page());
        return "admin/pages/new";
    }



    @PostMapping(path = "/admin/savepage")
    public String savePage(@Valid Page page, BindingResult bindingResult, Model model) {
        
        return "redirect:admin/pages";
    }
}
