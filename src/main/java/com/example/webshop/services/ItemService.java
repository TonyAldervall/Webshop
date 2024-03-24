package com.example.webshop.services;

import com.example.webshop.database.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepo repo;
    public List<Item> findAllByCategoryId(int id){
        return repo.findAllByCategory_Id(id);
    }

}
