package com.example.webshop;

import com.example.webshop.entity.*;
import com.example.webshop.repository.CartItemRepo;
import com.example.webshop.repository.ItemRepo;
import com.example.webshop.services.AccountSessionManager;
import com.example.webshop.services.CartItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CartItemServiceTest {
    @Mock
    private Account mockAccount;
    @Mock
    private List<CartItem> mockItemList;
    @Mock
    private Item mockItem;
    @Mock
    private CartItemRepo cartItemRepo;
    @Mock
    private ItemRepo itemRepo;
    @Mock
    private AccountSessionManager manager;
    @InjectMocks
    private CartItemService service;

    @BeforeEach
    public void setUp() {
        mockAccount = new Account("test", "test", "test123");
        mockAccount.setId(1);
        manager.setCurrentUser(mockAccount);
        Category fakeCategory = new Category("Fake");
        Item fakeItem1 = new Item("FakeItem1", 499, fakeCategory);
        Item fakeItem2 = new Item("FakeItem2", 3950, fakeCategory);
        Item fakeItem3 = new Item("FakeItem3", 1299, fakeCategory);
        fakeItem2.setId(1);
        mockItem = fakeItem2;
        mockItemList = new ArrayList<>();
        mockItemList.add(new CartItem(mockAccount.getCart(), fakeItem1, 1));
        mockItemList.add(new CartItem(mockAccount.getCart(), fakeItem2, 2));
        mockItemList.add(new CartItem(mockAccount.getCart(), fakeItem3, 3));
    }

    @Test
    public void testGetTotalSum() {
        when(manager.getCurrentUser()).thenReturn(mockAccount);
        when(cartItemRepo.findCartItemsByCart_Account_Id(1)).thenReturn(new ArrayList<>(mockItemList));

        long totalSum = service.getTotalSum();
        long expectedTotalSum = (499 + (3950 * 2) + (1299 * 3));
        assertEquals(expectedTotalSum, totalSum);
    }

    @Test
    public void testAddItemToCart(){
        when(itemRepo.findById(1)).thenReturn(mockItem);
        when(manager.getCurrentUser()).thenReturn(mockAccount);
        when(cartItemRepo.save(mockItemList.get(1))).thenReturn(mockItemList.get(0));
        service.addItemToCart(mockItem.getId(), 1);
    }
}
