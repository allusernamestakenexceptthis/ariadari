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

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PageService {
    
    private final PageRepository pageRepository;
    
    /**
     * Gets all published pages from the database
     * 
     * @return Iterable<Page>
     */
    public Iterable<Page> getPublishedPages() {
        return pageRepository.findByPublished(true);
    }

    /**
     * Gets all pages from the database
     * 
     * @return Iterable<Page>
     */
    public Iterable<Page> getAllPages() {
        return pageRepository.findAll();
    }

    /**
     * Saves a page to the database
     * 
     * @param page Page
     * @return boolean true if saved successfully
     */
    public boolean savePage(Page page) {
        Page savedPage = pageRepository.save(page);
        return savedPage != null;
    }

    
    /**
     * Checks if a page exists in the database using slug
     * 
     * @param slug
     * @return boolean
     */
    public boolean doesPageExist(String slug) {
        return pageRepository.existsBySlug(slug);
    }

    /**
     * Checks if a page exists in the database using slug
     * 
     * @param slug
     * @return boolean
     */
    public boolean doesPageExist(String slug, Long id) {
        if (id == null) {
            return pageRepository.existsBySlug(slug);
        } else {
            return pageRepository.existsBySlugAndIdNot(slug, id);
        }
    }

    /**
     * Gets a page by its slug
     * 
     * @param id Integer
     * @return Page
     */
    public Optional<Page> getPageById(Long id) {
        return pageRepository.findById(id);
    }

}
