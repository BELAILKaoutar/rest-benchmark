package org.rb.controllers;
import org.rb.entities.*;
import org.rb.repositories.*; 
import org.springframework.web.bind.annotation.*; 
import org.springframework.http.*; 
import java.time.Instant;

@RestController 
@RequestMapping("/items")

public class ItemController {
 private final ItemRepository items;
 private final CategoryRepository cats;
 public ItemController(ItemRepository i,CategoryRepository c){items=i;cats=c;}


 @GetMapping
  public Object list(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "20") int size) {
      return items.findAll(org.springframework.data.domain.PageRequest.of(page, size));
  }


 @GetMapping("/{id}") 
 public ResponseEntity<Item> one(@PathVariable Long id){
  return items.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); 
 }
 @GetMapping(params={"categoryId","page","size"})
 public Object byCat(@RequestParam Long categoryId,@RequestParam int page,@RequestParam int size){
  return items.findByCategoryId(categoryId,org.springframework.data.domain.PageRequest.of(page,size)); 
 }
 @PostMapping 
 public ResponseEntity<Item> create(@RequestBody Item b){
  Category c=cats.findById(b.getCategory().getId()).orElseThrow();
  b.setCategory(c); b.setUpdatedAt(Instant.now());
  return ResponseEntity.status(HttpStatus.CREATED).body(items.save(b)); 
 }
}