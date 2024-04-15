package com.example.webshop.repository;

import com.example.webshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Order findFirstByAccount_IdOrderByIdDesc(int id);
    List<Order> findAllByCompleteIsFalseOrderByIdAsc();
    List<Order> findAllByCompleteIsTrueOrderByIdDesc();
    Order findById(int id);
}
