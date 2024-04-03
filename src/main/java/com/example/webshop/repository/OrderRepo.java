package com.example.webshop.repository;

import com.example.webshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order findFirstByAccount_IdOrderByIdDesc(int id);
}
