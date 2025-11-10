package org.rb.repositories;

import org.rb.entities.Item; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByCategoryId(Long categoryId, Pageable p);
}