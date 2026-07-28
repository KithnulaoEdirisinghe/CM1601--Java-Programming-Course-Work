package com.example.cm1601_cw;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DealerSelectorTest {

    private List<Dealer> makeSampleDealers() {
        List<Dealer> dealers = new ArrayList<>();
        String[][] data = {
                {"D101", "Sunil Motors", "Malabe"},
                {"D102", "Kaduwela Spares Hub", "Kaduwela"},
                {"D103", "Ranatunga Auto", "Pittugala"},
                {"D104", "Maharagama Tuk Parts", "Maharagama"},
                {"D105", "Nimal & Sons", "Malabe"},
                {"D106", "Athurugiriya Auto", "Athurugiriya"},
                {"D107", "Koswatta Three-Wheelers", "Koswatta"},
                {"D108", "Weliweriya Spares", "Weliweriya"}
        };
        for (String[] row : data) {
            Dealer d = new Dealer();
            d.setDealerCode(row[0]);
            d.setDealerName(row[1]);
            d.setDealerLocation(row[2]);
            dealers.add(d);
        }
        return dealers;
    }

    @Test
    void selectsExactlyFourDealers() {
        List<Dealer> selected = DealerSelector.randomDealers(makeSampleDealers(), 4);
        assertEquals(4, selected.size());
    }

    @Test
    void selectsUniqueDealers() {
        List<Dealer> selected = DealerSelector.randomDealers(makeSampleDealers(), 4);

        Set<String> codes = new HashSet<>();
        for (Dealer d : selected) {
            codes.add(d.getDealerCode());
        }

        assertEquals(4, codes.size());
    }

    @Test
    void sortsSelectedDealersByLocation() {
        List<Dealer> selected = DealerSelector.randomDealers(makeSampleDealers(), 4);

        for (int i = 0; i < selected.size() - 1; i++) {
            String current = selected.get(i).getDealerLocation();
            String next = selected.get(i + 1).getDealerLocation();
            assertTrue(current.compareToIgnoreCase(next) <= 0);
        }
    }
}
