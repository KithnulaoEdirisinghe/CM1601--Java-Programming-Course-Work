package FX;

import com.example.cm1601_cw.InventoryItem;
import com.example.cm1601_cw.InventorySorter;
import com.example.cm1601_cw.LowStockMonitor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableCell;
import java.io.File;

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
    @FXML private TableColumn<InventoryItem, Integer> itemLowStockThreshold;

    @FXML private Label totalCountLabel;
    @FXML private Label totalValueLabel;
    @FXML private Label lowStockLabel;

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
        itemImage.setCellFactory(column -> new TableCell<InventoryItem, String>() {
            private final ImageView imageView = new ImageView();
            private final File noImageFile = new File("images/no image.png");

            @Override
            protected void updateItem(String filename, boolean empty) {
                super.updateItem(filename, empty);

                if (empty) {
                    setGraphic(null);
                    return;
                }

                File file;
                if (filename == null || filename.equals("None")) {
                    file = noImageFile;
                } else {
                    File candidate = new File("images/" + filename);
                    file = candidate.exists() ? candidate : noImageFile;
                }

                try {
                    imageView.setImage(new Image(file.toURI().toString()));
                    imageView.setFitHeight(40);
                    imageView.setFitWidth(40);
                    setGraphic(imageView);
                } catch (Exception e) {
                    setGraphic(null);
                }
            }
        });
        itemLowStockThreshold.setCellValueFactory(new PropertyValueFactory<>("lowStockThreshold"));
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

        List<InventoryItem> sorted = InventorySorter.sortInventory(inventory);
        List<InventoryItem> lowStock = LowStockMonitor.findLowStockItems(inventory);

        StringBuilder code = new StringBuilder();
        for (int i=0; i<lowStock.size(); i++) {
            code.append(lowStock.get(i).getItemCode());
            if (i < lowStock.size() - 1) {
                code.append(", ");
            }
        }
        lowStockLabel.setText ("Low stock : " + code);
        inventoryTable.setItems(FXCollections.observableArrayList(sorted));

        int count = 0;
        double totalValue = 0.0;
        for (InventoryItem item : inventory) {
            count++;
            totalValue += item.getItemPrice() * item.getItemQuantity();
        }
        totalCountLabel.setText(String.valueOf(count));
        totalValueLabel.setText(String.format("%.2f", totalValue));
    }
}