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
        assertEquals(1, mockAccount.getCart().getCartItems().size());
    }
}
