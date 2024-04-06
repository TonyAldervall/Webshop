package com.example.webshop.services;

import com.example.webshop.entity.Account;
import com.example.webshop.entity.Admin;
import com.example.webshop.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepo repo;
    @Autowired
    AdminSessionManager manager;
    public boolean adminLogin(String username, String password){
        List<Admin> adminList = repo.findAll();
        boolean successful = false;

        for(Admin a : adminList){
            if (a.getUsername().equals(username) && a.getPassword().equals(password)){
                manager.setAdmin(a);
                successful = true;
                break;
            }
        }
        return successful;
    }
    public void adminLogout(){
        manager.setAdmin(null);
    }
    public boolean sessionNull(){
        return manager.getAdmin() == null;
    }
}
