package FX;

import com.example.cm1601_cw.InventoryItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddItemController extends InventoryWriteController {

    @FXML private TextField codeField;
    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private TextField categoryField;
    @FXML private DatePicker datePicker;
    @FXML private TextField imageField;
    @FXML private Label errorLabel;

    @FXML
    private void handleAddItem() {

        String itemCode = codeField.getText().trim().toUpperCase();
        if (itemCode.isEmpty()) {
            showError("Item code cannot be empty.");
            return;
        }
        if (!itemCode.matches("P\\d{3}")) {
            showError("Invalid item code format (P006).");
            return;
        }
        for (InventoryItem item : inventory) {
            if (item.getItemCode().equals(itemCode)) {
                showError("This item code already exists.");
                return;
            }
        }

        String itemName = nameField.getText().trim();
        if (itemName.isEmpty()) {
            showError("Item name cannot be empty.");
            return;
        }
        boolean hasLetter = false;
        for (char c : itemName.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
                break;
            }
        }
        if (!hasLetter) {
            showError("Item name cannot be only numbers.");
            return;
        }

        String itemBrand = brandField.getText().trim();
        if (itemBrand.isEmpty()) {
            showError("Item brand cannot be empty.");
            return;
        }
        boolean allDigits = true;
        for (char c : itemBrand.toCharArray()) {
            if (!Character.isDigit(c)) {
                allDigits = false;
                break;
            }
        }
        if (allDigits) {
            showError("Item brand cannot be only numbers.");
            return;
        }

        double itemPrice;
        try {
            itemPrice = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            showError("Invalid price. Please enter a number.");
            return;
        }
        if (itemPrice < 0) {
            showError("Price cannot be negative.");
            return;
        }

        int itemQuantity;
        try {
            itemQuantity = Integer.parseInt(quantityField.getText().trim());
        } catch (NumberFormatException e) {
            showError("Invalid quantity. Please enter a whole number.");
            return;
        }
        if (itemQuantity < 0) {
            showError("Quantity cannot be negative.");
            return;
        }

        String itemCategory = categoryField.getText().trim();
        if (itemCategory.isEmpty()) {
            showError("Item category cannot be empty.");
            return;
        }
        boolean allLetters = true;
        for (char c : itemCategory.toCharArray()) {
            if (!Character.isLetter(c)) {
                allLetters = false;
                break;
            }
        }
        if (!allLetters) {
            showError("Item category must contain only letters.");
            return;
        }

        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null) {
            showError("Please select a purchase date.");
            return;
        }
        String itemDate = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String itemImage = imageField.getText().trim();
        if (itemImage.isEmpty()) {
            showError("Item image filename cannot be empty.");
            return;
        }

        InventoryItem newItem = new InventoryItem();
        newItem.setItemCode(itemCode);
        newItem.setItemName(itemName);
        newItem.setItemBrand(itemBrand);
        newItem.setItemPrice(itemPrice);
        newItem.setItemQuantity(itemQuantity);
        newItem.setItemCategory(itemCategory);
        newItem.setItemDate(itemDate);
        newItem.setItemImage(itemImage);

        inventory.add(newItem);

        if (!rewriteInventoryFile()) {
            inventory.remove(newItem);
            return;
        }

        errorLabel.setStyle("-fx-text-fill: green;");
        errorLabel.setText("Item added successfully.");

        codeField.clear();
        nameField.clear();
        brandField.clear();
        priceField.clear();
        quantityField.clear();
        categoryField.clear();
        datePicker.setValue(null);
        imageField.clear();
    }

    private void showError(String message) {
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setText(message);
    }

    @Override
    protected void onSavedFailed() {
        showError("Item added, but failed to save to file.");
    }
}
