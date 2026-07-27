package FX;

import com.example.cm1601_cw.Dealer;
import com.example.cm1601_cw.InventoryItem;
import javafx.fxml.FXML;

import java.util.List;

public class MainController {

    @FXML private ManageItemsController manageItemsController;
    @FXML private InventoryViewController inventoryViewController;
    @FXML private DealerController dealerViewController;
    @FXML private SearchController searchViewController;
    @FXML private CheckoutController checkoutViewController;

    public void setInventory(List<InventoryItem> inventory) {
        manageItemsController.setInventory(inventory);
        inventoryViewController.setInventory(inventory);
        searchViewController.setInventory(inventory);
        checkoutViewController.setInventory(inventory);
    }

    public void setDealers(List<Dealer> dealers) {
        dealerViewController.setDealers(dealers);
    }
}
