package com.gomilkyway.profile.adari.pages;

import org.springframework.stereotype.Service;

@Service
public class PageService {
    
    private PageRepository pageRepository;
    
    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }
    
    public Iterable<Page> getPublishedPages() {
        return pageRepository.findByPublished(true);
    }

    public boolean savePage(Page page) {
        Page savedPage = pageRepository.save(page);
        return savedPage != null;
    }

}
