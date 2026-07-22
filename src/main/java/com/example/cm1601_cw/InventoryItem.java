package com.example.cm1601_cw;

public class InventoryItem {

    private String itemCode;
    private String name;
    private String brand;
    private double price;
    private int quantity;
    private String category;
    private String date;
    private String image;

    // Setter methods
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemName(String name) {
        this.name = name;
    }

    public void setItemBrand(String brand) {
        this.brand = brand;
    }

    public void setItemPrice(double price) {
        if (price < 0) {
            System.out.println("Invalid price");
        } else {
            this.price = price;
        }
    }

    public void setItemQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Invalid quantity");
        } else {
            this.quantity = quantity;
        }
    }

    public void setItemCategory(String category) {
        this.category = category;
    }

    public void setItemDate(String date) {
        this.date = date;
    }

    public void setItemImage(String image) {
        this.image = image;
    }

    // Getter methods
    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return name;
    }

    public String getItemBrand() {
        return brand;
    }

    public double getItemPrice() {
        return price;
    }

    public int getItemQuantity() {
        return quantity;
    }

    public String getItemCategory() {
        return category;
    }

    public String getItemDate() {
        return date;
    }

    public String getItemImage() {
        return image;
    }

    // Display method
    public void displayInfo() {
        System.out.println("Item Code: " + itemCode);
        System.out.println("Item Name: " + name);
        System.out.println("Item Brand: " + brand);
        System.out.println("Item Price: " + price);
        System.out.println("Item Quantity: " + quantity);
        System.out.println("Item Category: " + category);
        System.out.println("Item Date: " + date);
        System.out.println("Item Image: " + image);
    }
}
