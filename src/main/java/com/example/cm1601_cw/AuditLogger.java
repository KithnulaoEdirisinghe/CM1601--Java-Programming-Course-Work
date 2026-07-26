package com.example.cm1601_cw;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLogger {

    private static final DateTimeFormatter timestampformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String action, String itemCode, int itemQuantity) {
        String timestamp = LocalDateTime.now().format(timestampformat);
        String line = timestamp + " | " + action + " | " + itemCode + " | " + itemQuantity;

        try (PrintWriter writer = new PrintWriter(new FileWriter("audit_log.txt", true))){
            writer.println(line);
        }
        catch (IOException e) {
            System.out.println("Failed to write audit log.");
        }
    }
}
