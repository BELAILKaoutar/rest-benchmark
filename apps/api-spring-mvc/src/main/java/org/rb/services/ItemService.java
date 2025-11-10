package org.rb.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.rb.entities.Item;
import org.rb.repositories.ItemRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ItemService {

    @Value("${rb.join-fetch:false}")
    private boolean joinFetch;

    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public Page<Item> list(Pageable p) {
        return repo.findAll(p);
    }

    public Page<Item> byCategory(Long cid, Pageable p) {
        if (joinFetch) {
            List<Item> data = repo.fetchJoinByCategory(cid, p);
            return new PageImpl<>(data, p, data.size());
        }
        return repo.findByCategoryId(cid, p);
    }
}
