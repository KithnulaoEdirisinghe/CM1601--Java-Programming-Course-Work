package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    void calculatesSubTotal() {
        InventoryItem item = new InventoryItem();
        item.setItemPrice(100.0);

        CartItem cartItem = new CartItem(item, 3);

        assertEquals(300.0, cartItem.getSubTotal());
    }

    @Test
    void exposesUnderlyingItemDetails() {
        InventoryItem item = new InventoryItem();
        item.setItemCode("P001");
        item.setItemName("Piston");
        item.setItemBrand("Bajaj");
        item.setItemPrice(4500.0);

        CartItem cartItem = new CartItem(item, 2);

        assertEquals("P001", cartItem.getItemCode());
        assertEquals("Piston", cartItem.getItemName());
        assertEquals("Bajaj", cartItem.getItemBrand());
        assertEquals(4500.0, cartItem.getItemPrice());
        assertEquals(2, cartItem.getQuantity());
    }
}
