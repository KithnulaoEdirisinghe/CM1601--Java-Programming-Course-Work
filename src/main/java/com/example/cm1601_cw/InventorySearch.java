package com.example.cm1601_cw;

import java.util.ArrayList;
import java.util.List;

public class InventorySearch {

    public static List<InventoryItem> search(List<InventoryItem> items, String category, double minPrice, double maxPrice, String keyword) {

        List<InventoryItem> results = new ArrayList<>();

        for (InventoryItem item : items) {
            boolean matchesCategory = category.isEmpty() || item.getItemCategory().equalsIgnoreCase(category);
            boolean matchesPrice = item.getItemPrice() >= minPrice && item.getItemPrice() <= maxPrice;
            boolean matchesKeyword = keyword.isEmpty() || item.getItemName().toLowerCase().contains(keyword.toLowerCase());

            if (matchesCategory && matchesPrice && matchesKeyword) {
                results.add(item);
            }
        }
        return results;
    }
}
