package com.example.cm1601_cw;

import java.util.ArrayList;
import java.util.List;

public class InventorySorter {

    public static List<InventoryItem> sortInventory(List<InventoryItem> items){

        List<InventoryItem> sortedInventory = new ArrayList<>(items);
        int n = sortedInventory.size();

        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < n-1-i; j++){
                InventoryItem a = sortedInventory.get(j);
                InventoryItem b = sortedInventory.get(j+1);

                int category = a.getItemCategory().compareToIgnoreCase(b.getItemCategory());
                boolean swap = category != 0 ? category > 0 : a.getItemCode().compareTo(b.getItemCode()) > 0;

                if(swap){
                    sortedInventory.set(j, b);
                    sortedInventory.set(j+1, a);
                }
            }
        }
        return sortedInventory;
    }

}
