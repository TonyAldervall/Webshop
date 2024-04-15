package com.example.webshop.services;

import com.example.webshop.entity.Account;
import com.example.webshop.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    AccountSessionManager accountSessionManager;
    public boolean login(String email, String password){
        List<Account> list = accountRepo.findAll();
        boolean successful = false;

        for(Account a : list){
            if (a.getEmail().equals(email) && a.getPassword().equals(password)){
                accountSessionManager.setCurrentUser(a);
                successful = true;
                break;
            }
        }
        return successful;
    }
    public void logout(){
        accountSessionManager.setCurrentUser(null);
    }

    public boolean accountExists(String email){
        List<Account> list = accountRepo.findAll();
        boolean exists = false;

        for(Account a : list){
            if (a.getEmail().equals(email)){
                exists = true;
                break;
            }
        }
        return exists;
    }
    public void createAccount(String email, String username, String password){
        Account account = new Account(email, username, password);
        accountRepo.save(account);
        System.out.println("Account created and saved.");
    }
    public boolean sessionNull(){
        return accountSessionManager.getCurrentUser() == null;
    }
}
