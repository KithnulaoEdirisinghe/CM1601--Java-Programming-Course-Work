package com.example.cm1601_cw;

import java.io.*;
import java.util.Arrays;

public class DealerReadFile {

    public DealerReadFile() {

        String path = "dealers_legacy.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(path));
            BufferedWriter writer = new BufferedWriter(new FileWriter("dealers_cleaned.txt"))){

            String line;
            while ((line = reader.readLine()) != null){

                String split;

                if (line.contains(";")){
                    split = ";";
                } else if (line.contains(",")){
                    split = ",";
                } else {
                    split = "[|]";
                }

                String [] dealers = line.split(split);

                for(int i=0; i<dealers.length; i++){
                    dealers[i] = dealers[i].trim();

                    if (dealers[i].isEmpty()){
                        dealers[i] = "None";
                    }

                }

                for(int i=0; i<dealers.length; i++){
                    writer.write(dealers[i]);
                    if(i<dealers.length-1){
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
