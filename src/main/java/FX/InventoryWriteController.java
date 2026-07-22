package FX;

import com.example.cm1601_cw.InventoryItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class InventoryWriteController {

    protected List<InventoryItem> inventory;

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    protected boolean rewriteInventoryFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory_cleaned.txt"))) {

            for (InventoryItem item : inventory) {
                writer.write(item.getItemCode() + ", " + item.getItemName() + ", " + item.getItemBrand() + ", " + String.format("%.2f", item.getItemPrice()) + ", " + item.getItemQuantity() + ", " + item.getItemCategory() + ", " + item.getItemDate() + ", " + item.getItemImage());writer.newLine();
            }
            return true;
        }
        catch (Exception e) {
            onSavedFailed();
            return false;
        }
    }
    protected abstract void onSavedFailed();
}
