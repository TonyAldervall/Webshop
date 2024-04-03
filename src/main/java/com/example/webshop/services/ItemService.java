package com.example.webshop.services;

import com.example.webshop.entity.Item;
import com.example.webshop.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepo repo;
    public List<Item> findAllByCategoryId(int id){
        return repo.findAllByCategory_Id(id);
    }

    public String getPageInfo(@PathVariable String category, @PathVariable int itemId, Model m) {
        Item item = repo.findById(itemId);
        m.addAttribute("title", "Tony's Webshop - " + item.getName());
        m.addAttribute("name", item.getName());
        m.addAttribute("price", item.getPrice());
        m.addAttribute("item", item);
        m.addAttribute("url", "/category/" + category + "/" + itemId);
        return "itempage";
    }
    public List<Item> itemContainsSearch(String search){
        List<Item> itemList = repo.findAll();
        List<Item> matchingList = new ArrayList<>();

        for(Item i : itemList){
            if(i.getName().toLowerCase().contains(search.toLowerCase())){
                matchingList.add(i);
            }
        }

        return matchingList;
    }
}
