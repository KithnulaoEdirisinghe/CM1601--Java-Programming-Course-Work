package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventorySearchTest {

    private List<InventoryItem> makeSampleItems() {
        List<InventoryItem> items = new ArrayList<>();

        InventoryItem piston = new InventoryItem();
        piston.setItemCode("P001");
        piston.setItemName("Bajaj Piston");
        piston.setItemCategory("Engine");
        piston.setItemPrice(4500.0);
        items.add(piston);

        InventoryItem brakePad = new InventoryItem();
        brakePad.setItemCode("P002");
        brakePad.setItemName("TVS Brake Pad");
        brakePad.setItemCategory("Brakes");
        brakePad.setItemPrice(1250.0);
        items.add(brakePad);

        InventoryItem sparkPlug = new InventoryItem();
        sparkPlug.setItemCode("P004");
        sparkPlug.setItemName("Spark Plug NGK");
        sparkPlug.setItemCategory("electrical");
        sparkPlug.setItemPrice(850.0);
        items.add(sparkPlug);

        return items;
    }

    @Test
    void filtersByCategoryOnly() {
        List<InventoryItem> results = InventorySearch.search(makeSampleItems(), "Engine", 0, Double.MAX_VALUE, "");
        assertEquals(1, results.size());
        assertEquals("P001", results.get(0).getItemCode());
    }

    @Test
    void filtersByPriceRangeOnly() {
        List<InventoryItem> results = InventorySearch.search(makeSampleItems(), "", 1000, 5000, "");
        assertEquals(2, results.size());
    }

    @Test
    void filtersByKeywordOnly() {
        List<InventoryItem> results = InventorySearch.search(makeSampleItems(), "", 0, Double.MAX_VALUE, "spark");
        assertEquals(1, results.size());
        assertEquals("P004", results.get(0).getItemCode());
    }

    @Test
    void combinesAllThreeFilters() {
        List<InventoryItem> results = InventorySearch.search(makeSampleItems(), "electrical", 0, 1000, "spark");
        assertEquals(1, results.size());
        assertEquals("P004", results.get(0).getItemCode());
    }

    @Test
    void returnsNoMatchesWhenNothingFits() {
        List<InventoryItem> results = InventorySearch.search(makeSampleItems(), "Dealer", 0, Double.MAX_VALUE, "");
        assertTrue(results.isEmpty());
    }
}
