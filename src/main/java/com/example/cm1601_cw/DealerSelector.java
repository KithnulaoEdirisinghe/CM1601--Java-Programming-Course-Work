package com.example.cm1601_cw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DealerSelector {

    public static List<Dealer> randomDealers(List<Dealer> allDealers, int count) {
        List<Dealer> selected = new ArrayList<>();
        List<String> usedCodes = new ArrayList<>();
        Random random = new Random();

        while (selected.size() < count) {
            int index = random.nextInt(allDealers.size());
            Dealer currentDealer = allDealers.get(index);

            if (!usedCodes.contains(currentDealer.getDealerCode())) {
                selected.add(currentDealer);
                usedCodes.add(currentDealer.getDealerCode());
            }
        }
        return sortByLocation(selected);
    }

    private static List<Dealer> sortByLocation(List<Dealer> allDealers) {
        List<Dealer> sortedDealers = new ArrayList<>(allDealers);
        int n = sortedDealers.size();

        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                Dealer a = sortedDealers.get(j);
                Dealer b = sortedDealers.get(j+1);

                if (a.getDealerLocation().compareToIgnoreCase(b.getDealerLocation()) > 0) {
                    sortedDealers.set(j, b);
                    sortedDealers.set(j+1, a);
                }
            }
        }
        return sortedDealers;
    }
}
