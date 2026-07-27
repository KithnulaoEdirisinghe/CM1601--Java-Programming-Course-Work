package FX;

import com.example.cm1601_cw.InventoryItem;
import com.example.cm1601_cw.InventorySearch;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SearchController {

    @FXML private TextField categoryField;
    @FXML private TextField minPriceField;
    @FXML private TextField maxPriceField;
    @FXML private TextField keyWordField;
    @FXML private Label errorLabel;

    @FXML private TableView<InventoryItem> searchTableView;
    @FXML private TableColumn<InventoryItem, String> resultCode;
    @FXML private TableColumn<InventoryItem, String> resultName;
    @FXML private TableColumn<InventoryItem, String> resultCategory;
    @FXML private TableColumn<InventoryItem, Double> resultPrice;
    @FXML private TableColumn<InventoryItem, Integer> resultQuantity;

    private List<InventoryItem> inventory;

    @FXML private void initialize() {

        resultCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        resultName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        resultCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        resultPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        resultQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    @FXML
    private void handleSearch() {

        String category = categoryField.getText().trim();
        String keyword = keyWordField.getText().trim();

        double minPrice;
        double maxPrice;

        try {
            minPrice = minPriceField.getText().trim().isEmpty() ? 0 : Double.parseDouble(minPriceField.getText().trim());
        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid minimum price.");
            return;
        }

        try {
            maxPrice = maxPriceField.getText().trim().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxPriceField.getText().trim());
        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid maximum price.");
            return;
        }

        if (minPrice > maxPrice) {
            errorLabel.setText("Minimum price cannot be greater than maximum price.");
            return;
        }

        List<InventoryItem> results = InventorySearch.search(inventory, category, minPrice, maxPrice, keyword);
        searchTableView.setItems(FXCollections.observableList(results));
        errorLabel.setText("Found " + results.size() + " item(s).");
    }
}
