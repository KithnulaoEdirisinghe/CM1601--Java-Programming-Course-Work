package com.example.cm1601_cw;

import java.util.List;

public class DiscountCalculator {

    public static double calculateTotal (List<CartItem> items) {
        double total = 0.0;

        boolean hasEngine = false;
        boolean hasElectrical = false;

        for (CartItem cartItem : items) {

            double subtotal = cartItem.getSubTotal();

            if (cartItem.getQuantity() >= 3) {

                subtotal = subtotal * 0.95;
            }

            total += subtotal;

            String category = cartItem.getItem().getItemCategory();

            if (category.equalsIgnoreCase("Engine")) {
                hasEngine = true;
            }

            if (category.equalsIgnoreCase("Electrical")) {
                hasElectrical = true;
            }
        }

        if (hasEngine && hasElectrical) {

            total = total * 0.90;
        }
        return total;
    }
}
