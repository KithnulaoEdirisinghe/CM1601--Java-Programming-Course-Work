package com.example.cm1601_cw;

public class CartItem {

    private InventoryItem item;
    private int quantity;

    public CartItem(InventoryItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public InventoryItem getItem() {
        return item;
    }

    public String getItemCode() {
        return item.getItemCode();
    }

    public String getItemName() {
        return item.getItemName();
    }

    public String getItemBrand() {
        return item.getItemBrand();
    }

    public double getItemPrice() {
        return item.getItemPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return item.getItemPrice() * quantity;
    }
}
