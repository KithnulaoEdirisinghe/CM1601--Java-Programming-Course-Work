package FX;

import com.example.cm1601_cw.InventoryItem;
import javafx.fxml.FXML;

import java.util.List;

public class MainController {

    @FXML private ManageItemsController manageItemsController;
    @FXML private InventoryViewController inventoryViewController;   // added

    public void setInventory(List<InventoryItem> inventory) {
        manageItemsController.setInventory(inventory);
        inventoryViewController.setInventory(inventory);   // added
    }
}
