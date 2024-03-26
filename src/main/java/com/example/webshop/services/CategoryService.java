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
    public int getCategoryId(String category){
        switch(category.toLowerCase()){
            case "headphones" -> {
                return 1;
            }
            case "speakers" -> {
                return 2;
            }
            case "computers" -> {
                return 3;
            }
            case "monitors" -> {
                return 4;
            }
            case "mice" -> {
                return 5;
            }
            case "keyboards" -> {
                return 6;
            }
            case "laptops" -> {
                return 7;
            }
            case "phones" -> {
                return 8;
            }
            case "smartwatches" -> {
                return 9;
            }
            default -> {
                return -1;
            }
        }
    }
    public String getCategoryName(int categoryId){
        switch (categoryId){
            case 1 -> {
                return "Headphones";
            }
            case 2 -> {
                return "Speakers";
            }
            case 3 -> {
                return "Computers";
            }
            case 4 -> {
                return "Monitors";
            }
            case 5 -> {
                return "Mice";
            }
            case 6 -> {
                return "Keyboards";
            }
            case 7-> {
                return "Laptops";
            }
            case 8 -> {
                return "Phones";
            }
            case 9-> {
                return "Smartwatches";
            }
            default -> {
                return "Unknown";
            }
        }
    }
}
