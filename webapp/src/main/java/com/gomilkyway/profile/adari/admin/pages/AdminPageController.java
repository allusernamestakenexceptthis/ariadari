package com.gomilkyway.profile.adari.admin.pages;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gomilkyway.profile.adari.pages.Page;
import com.gomilkyway.profile.adari.pages.PageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminPageController {
    private final PageService pageService;
    
    private final MessageSource messageSource;

    /*
    public AdminPageController(PageService pageService, MessageSource messageSource) {
        this.pageService = pageService;
    }*/

    @GetMapping(path = "/admin/pages")
    public String getPages(Model model)  {
        model.addAttribute("pages", pageService.getAllPages());
        return "admin/pages";
    }

    @GetMapping(path = "/admin/pages/new")
    public String newPage(Model model)  {
        model.addAttribute("page", new Page());
        return "admin/pages/newOrEdit";
    }

    @GetMapping(path = "/admin/pages/edit/{id}")
    public String editPage(@PathVariable Long id, Model model)  {
        model.addAttribute("page", pageService.getPageById(id));
        return "admin/pages/newOrEdit";
    }


    @PostMapping(path = "/admin/page/savepage")
    public String savePage(@Valid @ModelAttribute("page") Page page, BindingResult bindingResult, Model model) {
        
        if (
            page.getSlug()!=null &&
            pageService.doesPageExist(page.getSlug(), page.getId())
        ) {
            bindingResult.rejectValue("slug", "", messageSource.getMessage("page.slug_exists", null, LocaleContextHolder.getLocale()));
        }

        if (bindingResult.hasErrors()) {
            return "admin/pages/newOrEdit";
        }

        pageService.savePage(page);

        return "redirect:/admin/pages?addedNewpage";
    }
}
