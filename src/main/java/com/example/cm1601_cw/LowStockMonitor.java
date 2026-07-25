package com.example.cm1601_cw;

import java.util.ArrayList;
import java.util.List;

public class LowStockMonitor {

    public static List<InventoryItem> findLowStockItems(List<InventoryItem> items) {
        List<InventoryItem> lowStockItems = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item.getItemQuantity() <= item.getLowStockThreshold()) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }
}
