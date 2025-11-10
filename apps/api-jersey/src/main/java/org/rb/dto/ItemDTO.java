package org.rb.dto;

import org.rb.entities.Item;
import java.math.BigDecimal;
import java.time.Instant;

public class ItemDTO {
    public Long id;
    public String sku;
    public String name;
    public BigDecimal price;
    public Integer stock;
    public String categoryName;
    public Instant updatedAt;

    // Constructeur à partir d'un Item
    public ItemDTO(Item item) {
        this.id = item.getId();
        this.sku = item.getSku();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.updatedAt = item.getUpdatedAt();
        this.categoryName = item.getCategory() != null ? item.getCategory().getName() : null;
    }
}
