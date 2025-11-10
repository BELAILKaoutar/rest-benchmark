package org.rb.repositories;

import org.rb.entities.Category; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>{}