package com.example.cm1601_cw;

import java.io.*;
import java.util.*;

public class CleanRead {

    private List<InventoryItem> inventory = new ArrayList<>();
    private List<List<String>> dealer = new ArrayList<>();

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public List<List<String>> getDealer() {
        return dealer;
    }

    public CleanRead() {

        String in_path = "inventory_cleaned.txt";
        String dl_path = "dealers_cleaned.txt";

        try (BufferedReader in = new BufferedReader(new FileReader(in_path));
             BufferedReader out = new BufferedReader(new FileReader(dl_path))) {

            String in_line;
            while ((in_line = in.readLine()) != null) {

                List<String> line = new ArrayList<>(Arrays.asList(in_line.split(",")));

                for (int i = 0; i < line.size(); i++) {
                    line.set(i, line.get(i).trim());
                }
                InventoryItem item = new InventoryItem();
                item.setItemCode(line.get(0));
                item.setItemName(line.get(1));
                item.setItemBrand(line.get(2));
                item.setItemPrice(Double.parseDouble(line.get(3)));
                item.setItemQuantity(Integer.parseInt(line.get(4)));
                item.setItemCategory(line.get(5));
                item.setItemDate(line.get(6));
                item.setItemImage(line.get(7));

                inventory.add(item);
            }

            String out_line;
            while ((out_line = out.readLine()) != null) {

                List<String> fields = new ArrayList<>(Arrays.asList(out_line.split(",")));

                for (int i = 0; i < fields.size(); i++) {
                    fields.set(i, fields.get(i).trim());
                }
                dealer.add(fields);

            }

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
    }
}
