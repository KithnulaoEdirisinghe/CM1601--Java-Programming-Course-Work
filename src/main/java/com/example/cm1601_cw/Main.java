package com.example.cm1601_cw;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        InventoryReadFile inreader = new InventoryReadFile();
        DealerReadFile dlreader = new DealerReadFile();
        CleanRead cleanreader = new CleanRead();

        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine().trim().toLowerCase();

            if(choice.equals("aid")){
                ItemManager.AID(cleanreader.inventory);
            }
            else if(choice.equals("uid")){
                ItemManager.UID();
            }
            else if(choice.equals("esc")) {
                break;
            }
            else{
                System.out.println("Invalid Input.\nPlease try again.");
            }
        }
    }
}
