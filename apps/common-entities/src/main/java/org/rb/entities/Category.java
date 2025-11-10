package org.rb.entities;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=32)
    private String code;

    @Column(nullable=false, length=128)
    private String name;



    @Column(name="updated_at", nullable=false)
    private Instant updatedAt = Instant.now();

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Item> items = new ArrayList<>();

    // getters et setters
    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setCode(String c) { code = c; }
    public void setName(String n) { name = n; }
    public void setUpdatedAt(Instant u) { updatedAt = u; }

    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}
