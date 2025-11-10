package org.rb.controllers;
import org.rb.entities.*; 
import org.rb.repositories.*; 
import org.springframework.web.bind.annotation.*; 
import org.springframework.http.*;
import java.time.Instant;

@RestController
@RequestMapping("/categories")

public class CategoryController {
 private final CategoryRepository repo;

 public CategoryController(CategoryRepository r){
    this.repo=r;
 }
 @GetMapping public Object list(@RequestParam int page,@RequestParam int size){
  return repo.findAll(org.springframework.data.domain.PageRequest.of(page,size)); }
 @GetMapping("/{id}") public ResponseEntity<Category> one(@PathVariable Long id){
  return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
 @PostMapping public Category create(@RequestBody Category c){ c.setUpdatedAt(Instant.now()); return repo.save(c); }
}