package com.example.cm1601_cw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {

    private InventoryItem item;

    @BeforeEach
    void setUp() {
        item = new InventoryItem();
    }

    @Test
    void acceptsValidPrice() {
        item.setItemPrice(100.0);
        assertEquals(100.0, item.getItemPrice());
    }

    @Test
    void rejectsNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> item.setItemPrice(-5));
    }

    @Test
    void acceptsValidQuantity() {
        item.setItemQuantity(10);
        assertEquals(10, item.getItemQuantity());
    }

    @Test
    void rejectsNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> item.setItemQuantity(-1));
    }

    @Test
    void acceptsValidLowStockThreshold() {
        item.setLowStockThreshold(5);
        assertEquals(5, item.getLowStockThreshold());
    }

    @Test
    void rejectsNegativeLowStockThreshold() {
        assertThrows(IllegalArgumentException.class, () -> item.setLowStockThreshold(-1));
    }
}
