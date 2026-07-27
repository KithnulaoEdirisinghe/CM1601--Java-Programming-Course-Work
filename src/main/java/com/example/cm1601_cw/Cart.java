package com.example.cm1601_cw;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public boolean addItem(InventoryItem item, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        if (quantity > item.getItemQuantity()) {
            return false;
        }

        items.add(new CartItem(item, quantity));
        return true;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void  clear() {
        items.clear();
    }
}
