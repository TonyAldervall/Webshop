package com.example.webshop.repository;

import com.example.webshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> findAllByCategory_Id(int category);
    Item findById(int id);
}
