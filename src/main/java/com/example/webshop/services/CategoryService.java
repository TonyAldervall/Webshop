package com.example.webshop.services;

import com.example.webshop.database.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepo repo;
    public List<Category> getAllCategories(){
        return repo.findAll();
    }
}
