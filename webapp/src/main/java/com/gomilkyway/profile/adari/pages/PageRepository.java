package com.gomilkyway.profile.adari.pages;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, Integer> {

    List<Page> findByPublished(boolean published);
    
}
