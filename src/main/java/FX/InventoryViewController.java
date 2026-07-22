package FX;

import com.example.cm1601_cw.InventoryItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class InventoryViewController {

    @FXML private TableView<InventoryItem> inventoryTable;
    @FXML private TableColumn<InventoryItem, String> itemCode;
    @FXML private TableColumn<InventoryItem, String> itemName;
    @FXML private TableColumn<InventoryItem, String> itemBrand;
    @FXML private TableColumn<InventoryItem, Double> itemPrice;
    @FXML private TableColumn<InventoryItem, Integer> itemQuantity;
    @FXML private TableColumn<InventoryItem, String> itemCategory;
    @FXML private TableColumn<InventoryItem, String> itemDate;
    @FXML private TableColumn<InventoryItem, String> itemImage;

    @FXML private Label totalCountLabel;
    @FXML private Label totalValueLabel;

    private List<InventoryItem> inventory;

    @FXML
    public void initialize() {
        itemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemBrand.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        itemQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        itemDate.setCellValueFactory(new PropertyValueFactory<>("itemDate"));
        itemImage.setCellValueFactory(new PropertyValueFactory<>("itemImage"));
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
        refreshTable();
    }

    @FXML
    private void handleRefresh() {
        refreshTable();
    }

    private void refreshTable() {
        // TODO: swap for InventorySorter.byCategoryThenCode(inventory) once that class exists
        List<InventoryItem> sorted = inventory;

        inventoryTable.setItems(FXCollections.observableArrayList(sorted));

        int count = 0;
        double totalValue = 0.0;
        for (InventoryItem item : inventory) {
            count++;
            totalValue += item.getItemPrice() * item.getItemQuantity();
        }
        totalCountLabel.setText(String.valueOf(count));                     // changed
        totalValueLabel.setText(String.format("%.2f", totalValue));         // changed
    }
}