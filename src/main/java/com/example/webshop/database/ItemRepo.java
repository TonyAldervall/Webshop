package com.example.webshop.database;

import com.example.webshop.services.Category;
import com.example.webshop.services.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    List<Item> findAllByCategory_Id(int category);
}
