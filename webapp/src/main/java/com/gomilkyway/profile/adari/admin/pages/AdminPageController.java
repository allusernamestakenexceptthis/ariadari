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

package com.gomilkyway.profile.adari.admin.pages;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gomilkyway.profile.adari.files.FiledbService;
import com.gomilkyway.profile.adari.pages.Page;
import com.gomilkyway.profile.adari.pages.PageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdminPageController {
    private final PageService pageService;
    
    private final MessageSource messageSource;

    private final FiledbService filedbService;

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

    @RequestMapping(path = "/admin/pages/uploadImage", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = "application/json")
    public ResponseEntity<Map<String, Object>> uploadImages(@RequestPart(name = "file") MultipartFile image ) {
        
        String filename = "";

        try {
            filename = filedbService.uploadImageAndSave(image);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

        return ResponseEntity.ok(Map.of("location", filename));
    }

}
