package org.rb.repositories;

import org.rb.entities.Item; 
import org.springframework.data.jpa.repository.*; 
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByCategoryId(Long categoryId, Pageable pageable);

    @Query("select i from Item i join fetch i.category c where c.id = :cid")
    List<Item> fetchJoinByCategory(@Param("cid") Long cid, Pageable pageable);
}
