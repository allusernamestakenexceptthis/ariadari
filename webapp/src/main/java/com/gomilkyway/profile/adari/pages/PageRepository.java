package com.gomilkyway.profile.adari.pages;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<Page, Integer> {
    List<Page> findByPublished(boolean published);
    Optional<Page> findBySlug(String slug);
    Optional<Page> findById(Long id);
    Boolean existsBySlug(String slug);
    Boolean existsBySlugAndIdNot(String slug, Long id);
}
