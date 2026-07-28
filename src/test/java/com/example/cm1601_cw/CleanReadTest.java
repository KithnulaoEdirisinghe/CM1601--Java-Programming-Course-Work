package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CleanReadTest {

    @Test
    void loadsInventoryItemsWithValidData() {
        CleanRead reader = new CleanRead();
        List<InventoryItem> inventory = reader.getInventory();

        assertFalse(inventory.isEmpty());
        for (InventoryItem item : inventory) {
            assertNotNull(item.getItemCode());
            assertNotNull(item.getItemName());
            assertTrue(item.getItemPrice() >= 0);
            assertTrue(item.getItemQuantity() >= 0);
            assertTrue(item.getLowStockThreshold() >= 0);
        }
    }

    @Test
    void loadsDealersWithValidData() {
        CleanRead reader = new CleanRead();
        List<Dealer> dealers = reader.getDealer();

        assertFalse(dealers.isEmpty());
        for (Dealer dealer : dealers) {
            assertNotNull(dealer.getDealerCode());
            assertNotNull(dealer.getDealerName());
        }
    }
}
