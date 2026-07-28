package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {

    private InventoryItem makeItem(String category, double price) {
        InventoryItem item = new InventoryItem();
        item.setItemCategory(category);
        item.setItemPrice(price);
        return item;
    }

    @Test
    void noDiscountAppliesForSmallSingleCategoryOrder() {
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(makeItem("Brakes", 1000), 1));

        double total = DiscountCalculator.calculateTotal(cart);

        assertEquals(1000.0, total, 0.001);
    }

    @Test
    void bulkDiscountAppliesAtThreeOrMore() {
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(makeItem("Brakes", 1000), 3));

        double total = DiscountCalculator.calculateTotal(cart);

        assertEquals(2850.0, total, 0.001);
    }

    @Test
    void synergyDiscountAppliesWithEngineAndElectrical() {
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(makeItem("Engine", 1000), 1));
        cart.add(new CartItem(makeItem("Electrical", 1000), 1));

        double total = DiscountCalculator.calculateTotal(cart);

        assertEquals(1800.0, total, 0.001);
    }

    @Test
    void bothDiscountsApplyTogetherInCorrectOrder() {
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(makeItem("Engine", 1000), 3));
        cart.add(new CartItem(makeItem("Electrical", 1000), 1));

        double total = DiscountCalculator.calculateTotal(cart);

        assertEquals(3465.0, total, 0.001);
    }

    @Test
    void synergyDoesNotApplyWithOnlyOneQualifyingCategory() {
        List<CartItem> cart = new ArrayList<>();
        cart.add(new CartItem(makeItem("Engine", 1000), 1));
        cart.add(new CartItem(makeItem("Brakes", 1000), 1));

        double total = DiscountCalculator.calculateTotal(cart);

        assertEquals(2000.0, total, 0.001);
    }
}
