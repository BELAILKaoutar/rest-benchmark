package org.rb.entities;
import jakarta.persistence.*;
import java.math.BigDecimal; 
import java.time.Instant;

@Entity @Table(name="item")
public class Item {
 @Id 
 @GeneratedValue(strategy=GenerationType.IDENTITY) 
 private Long id;
 @Column(nullable=false,unique=true,length=64) 
 private String sku;
 @Column(nullable=false,length=128) 
 private String name;
 @Column(nullable=false,precision=10,scale=2) 
 private BigDecimal price;
 @Column(nullable=false) 
 private Integer stock;
 @ManyToOne(fetch=FetchType.LAZY) 
 @JoinColumn(name="category_id") 
 private Category category;
 @Column(name="updated_at",nullable=false) 
 private Instant updatedAt=Instant.now();
 public Long getId(){return id;} 
 public String getSku(){return sku;}
 public String getName(){return name;} 
 public BigDecimal getPrice(){return price;}
 public Integer getStock(){return stock;} 
 public Category getCategory(){return category;}
 public Instant getUpdatedAt(){return updatedAt;}
 public void setSku(String s){sku=s;} 
 public void setName(String n){name=n;}
 public void setPrice(BigDecimal p){price=p;} 
 public void setStock(Integer s){stock=s;}
 public void setCategory(Category c){category=c;} 
 public void setUpdatedAt(Instant u){updatedAt=u;}
}