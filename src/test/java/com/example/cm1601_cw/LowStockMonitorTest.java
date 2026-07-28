package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LowStockMonitorTest {

    private InventoryItem makeItem(String code, int quantity, int threshold) {
        InventoryItem item = new InventoryItem();
        item.setItemCode(code);
        item.setItemQuantity(quantity);
        item.setLowStockThreshold(threshold);
        return item;
    }

    @Test
    void flagsItemBelowThreshold() {
        List<InventoryItem> items = List.of(makeItem("P001", 2, 5));
        List<InventoryItem> lowStock = LowStockMonitor.findLowStockItems(items);
        assertEquals(1, lowStock.size());
    }

    @Test
    void flagsItemExactlyAtThreshold() {
        List<InventoryItem> items = List.of(makeItem("P001", 5, 5));
        List<InventoryItem> lowStock = LowStockMonitor.findLowStockItems(items);
        assertEquals(1, lowStock.size());
    }

    @Test
    void doesNotFlagItemAboveThreshold() {
        List<InventoryItem> items = List.of(makeItem("P001", 6, 5));
        List<InventoryItem> lowStock = LowStockMonitor.findLowStockItems(items);
        assertTrue(lowStock.isEmpty());
    }

    @Test
    void findsMultipleLowStockItems() {
        List<InventoryItem> items = new ArrayList<>();
        items.add(makeItem("P001", 2, 5));
        items.add(makeItem("P002", 10, 5));
        items.add(makeItem("P003", 0, 5));

        List<InventoryItem> lowStock = LowStockMonitor.findLowStockItems(items);

        assertEquals(2, lowStock.size());
    }
}
