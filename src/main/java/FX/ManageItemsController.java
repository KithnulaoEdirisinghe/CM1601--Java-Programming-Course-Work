package FX;

import com.example.cm1601_cw.InventoryItem;
import javafx.fxml.FXML;

import java.util.List;

public class ManageItemsController {

    @FXML private AddItemController addItemController;
    @FXML private UpdateItemController updateItemController;
    @FXML private DeleteItemController deleteItemController;

    public void setInventory(List<InventoryItem> inventory) {
        addItemController.setInventory(inventory);
        updateItemController.setInventory(inventory);
        deleteItemController.setInventory(inventory);
    }
}
