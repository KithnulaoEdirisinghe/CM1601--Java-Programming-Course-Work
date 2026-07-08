package com.example.cm1601_cw;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class InventoryReadFile {

    private static final DateTimeFormatter [] DateFromatters = {

            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH),
    };

    private static final DateTimeFormatter DoneDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public InventoryReadFile(){

        String path = "inventory_legacy.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(path));
        BufferedWriter writer = new BufferedWriter(new FileWriter("inventory_cleaned.txt"))){

            String line;
            while ((line = reader.readLine()) != null){

                int com = 0;
                int sem = 0;
                int das = 0;

                char[] letters = line.toCharArray();

                for(char letter : letters){
                    if(letter==','){
                        com++;
                    }
                    if(letter==';'){
                        sem++;
                    }
                    if(letter=='|'){
                        das++;
                    }
                }

                String simbol;

                if(com>=sem && com>=das){
                    simbol = ",";
                }
                else if(sem>=com && sem>=das){
                    simbol = ";";
                }
                else{
                    simbol = "\\|";
                }

                String[] items = line.split(String.valueOf(simbol),-1);

                List<String> finalItems = new ArrayList<>();

                for (String item : items) {

                    if (item.contains("|") || item.contains(";")) {
                        String[] subParts = item.split("[|;]",-1);
                        for (String sub : subParts) {
                            if(sub.trim().isEmpty()){
                                finalItems.add("None");
                            }
                            else{
                                finalItems.add(sub.trim());
                            }
                        }
                    } else if(item.trim().isEmpty()) {
                        finalItems.add("None");
                    }
                    else{
                        finalItems.add(item.trim());
                    }
                }

                String price = finalItems.get(3);

                if(!price.equals("None")){
                    String cleaned_price = price.replace("Rs.","").replace("Rs","").trim();
                    double priceDouble = Double.parseDouble(cleaned_price);
                    finalItems.set(3, String.format("%.2f",priceDouble));
                }

                String date = finalItems.get(6);

                if(!date.equals("None")){
                    for(DateTimeFormatter format : DateFromatters){
                        try{
                        LocalDate Date = LocalDate.parse(date.trim(),format);
                        finalItems.set(6, Date.format(DoneDate));
                        break;
                        } catch(DateTimeParseException e){
                            //not this format
                        }
                    }

                }

                for(int i=0; i<finalItems.size(); i++){
                    writer.write(finalItems.get(i));
                    if(i<finalItems.size()-1){
                        writer.write(", ");
                    }
                }

                writer.newLine();

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
