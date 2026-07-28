package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventorySorterTest {

    private InventoryItem makeItem(String code, String category) {
        InventoryItem item = new InventoryItem();
        item.setItemCode(code);
        item.setItemCategory(category);
        return item;
    }

    @Test
    void sortsByCategoryThenCode() {
        List<InventoryItem> items = new ArrayList<>();
        items.add(makeItem("P003", "Brakes"));
        items.add(makeItem("P001", "Engine"));
        items.add(makeItem("P002", "Engine"));

        List<InventoryItem> sorted = InventorySorter.sortInventory(items);

        assertEquals("P003", sorted.get(0).getItemCode());
        assertEquals("P001", sorted.get(1).getItemCode());
        assertEquals("P002", sorted.get(2).getItemCode());
    }

    @Test
    void groupsCategoriesCaseInsensitively() {
        List<InventoryItem> items = new ArrayList<>();
        items.add(makeItem("P002", "ENGINE"));
        items.add(makeItem("P001", "engine"));

        List<InventoryItem> sorted = InventorySorter.sortInventory(items);

        assertEquals("P001", sorted.get(0).getItemCode());
        assertEquals("P002", sorted.get(1).getItemCode());
    }

    @Test
    void doesNotMutateOriginalList() {
        List<InventoryItem> items = new ArrayList<>();
        items.add(makeItem("P002", "Engine"));
        items.add(makeItem("P001", "Engine"));

        InventorySorter.sortInventory(items);

        assertEquals("P002", items.get(0).getItemCode());
    }
}
