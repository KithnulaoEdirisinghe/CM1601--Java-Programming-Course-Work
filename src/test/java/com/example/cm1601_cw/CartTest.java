package com.example.cm1601_cw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private InventoryItem item;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        item = new InventoryItem();
        item.setItemCode("P001");
        item.setItemName("Test Part");
        item.setItemQuantity(10);
    }

    @Test
    void acceptsValidQuantity() {
        assertTrue(cart.addItem(item, 3));
        assertEquals(1, cart.getItems().size());
    }

    @Test
    void rejectsZeroQuantity() {
        assertFalse(cart.addItem(item, 0));
        assertTrue(cart.isEmpty());
    }

    @Test
    void rejectsNegativeQuantity() {
        assertFalse(cart.addItem(item, -2));
        assertTrue(cart.isEmpty());
    }

    @Test
    void rejectsQuantityExceedingStock() {
        assertFalse(cart.addItem(item, 11));
        assertTrue(cart.isEmpty());
    }

    @Test
    void emptyCartReportsEmpty() {
        assertTrue(cart.isEmpty());
    }

    @Test
    void clearRemovesAllItems() {
        cart.addItem(item, 2);
        cart.clear();
        assertTrue(cart.isEmpty());
    }
}
