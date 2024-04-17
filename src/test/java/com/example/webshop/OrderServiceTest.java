package com.example.webshop;

import com.example.webshop.entity.*;
import com.example.webshop.repository.CartItemRepo;
import com.example.webshop.repository.OrderItemRepo;
import com.example.webshop.repository.OrderRepo;
import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {
    @Mock
    private Account mockAccount;
    @Mock
    private List<CartItem> mockItemList;
    @Mock
    private Order mockOrder;
    @Mock
    private CartItemRepo cartItemRepo;
    @Mock
    private OrderRepo orderRepo;
    @Mock
    private OrderItemRepo orderItemRepo;
    @Mock
    private AccountSessionManager manager;
    @InjectMocks
    private OrderService service;

    @BeforeEach
    public void setUp() {
        mockAccount = new Account("Mock@mockmail.mock", "Mock", "Mock123");
        mockAccount.setId(1);
        manager.setCurrentUser(mockAccount);
        Category mockCategory = new Category("Mock");
        Item mockItem1 = new Item("MockItem1", 499, mockCategory);
        Item mockItem2 = new Item("MockItem2", 3950, mockCategory);
        Item mockItem3 = new Item("MockItem3", 1299, mockCategory);
        mockItem2.setId(1);
        mockItemList = new ArrayList<>();
        mockItemList.add(new CartItem(mockAccount.getCart(), mockItem1, 1));
        mockItemList.add(new CartItem(mockAccount.getCart(), mockItem2, 2));
        mockItemList.add(new CartItem(mockAccount.getCart(), mockItem3, 3));
        mockOrder = new Order(mockAccount);
        mockAccount.getOrder().add(mockOrder);
    }

    @Test
    public void createOrderTest(){
        when(orderRepo.save(new Order())).thenReturn(new Order());
        when(manager.getCurrentUser()).thenReturn(mockAccount);
        service.createOrder();
        assertEquals(2 ,mockAccount.getOrder().size());
    }
    @Test
    public void convertCartItemToOrderItemTest(){
        when(manager.getCurrentUser()).thenReturn(mockAccount);
        when(cartItemRepo.findCartItemsByCart_Account_Id(1)).thenReturn(mockItemList);
        when(orderRepo.findFirstByAccount_IdOrderByIdDesc(1)).thenReturn(mockOrder);
        when(orderItemRepo.save(new OrderItem())).thenReturn(new OrderItem());
        service.convertCartItemToOrderItem();
        assertEquals(3, mockOrder.getOrderItemList().size());
    }
    @Test
    public void clearCartTest(){
        when(manager.getCurrentUser()).thenReturn(mockAccount);
        mockAccount.getCart().getCartItems().add(mockItemList.get(0));
        mockAccount.getCart().getCartItems().add(mockItemList.get(1));
        mockAccount.getCart().getCartItems().add(mockItemList.get(2));
        doNothing().when(cartItemRepo).deleteAllInBatch(mockAccount.getCart().getCartItems());
        service.clearCart();
        assertEquals(0, mockAccount.getCart().getCartItems().size());
    }
}
