package FX;

import com.example.cm1601_cw.AuditLogger;
import com.example.cm1601_cw.InventoryItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateItemController extends InventoryWriteController {

    @FXML private TextField findCodeField;
    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField quantityField;
    @FXML private TextField categoryField;
    @FXML private DatePicker datePicker;
    @FXML private TextField imageField;
    @FXML private Label statusLabel;
    @FXML private TextField thresholdField;

    private InventoryItem foundItem;

    @FXML
    private void handleFind() {
        String code = findCodeField.getText().trim().toUpperCase();
        foundItem = null;

        for (InventoryItem item : inventory) {
            if (item.getItemCode().equals(code)) {
                foundItem = item;
                break;
            }
        }

        if (foundItem == null) {
            clearFields();
            showStatus("Item not found.", true);
            return;
        }

        nameField.setText(foundItem.getItemName());
        brandField.setText(foundItem.getItemBrand());
        priceField.setText(String.format("%.2f", foundItem.getItemPrice()));
        quantityField.setText(String.valueOf(foundItem.getItemQuantity()));
        categoryField.setText(foundItem.getItemCategory());
        datePicker.setValue(LocalDate.parse(foundItem.getItemDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        imageField.setText(foundItem.getItemImage());
        thresholdField.setText(String.valueOf(foundItem.getLowStockThreshold()));

        showStatus("Item found. Edit fields below and click Save.", false);
    }

    @FXML
    private void handleSave() {
        if (foundItem == null) {
            showStatus("Find an item first.", true);
            return;
        }

        String itemName = nameField.getText().trim();
        if (itemName.isEmpty()) {
            showStatus("Item name cannot be empty.", true);
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
            showStatus("Item name cannot be only numbers.", true);
            return;
        }

        String itemBrand = brandField.getText().trim();
        if (itemBrand.isEmpty()) {
            showStatus("Item brand cannot be empty.", true);
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
            showStatus("Item brand cannot be only numbers.", true);
            return;
        }

        double itemPrice;
        try {
            itemPrice = Double.parseDouble(priceField.getText().trim());
        } catch (NumberFormatException e) {
            showStatus("Invalid price. Please enter a number.", true);
            return;
        }
        if (itemPrice < 0) {
            showStatus("Price cannot be negative.", true);
            return;
        }

        int itemQuantity;
        try {
            itemQuantity = Integer.parseInt(quantityField.getText().trim());
        } catch (NumberFormatException e) {
            showStatus("Invalid quantity. Please enter a whole number.", true);
            return;
        }
        if (itemQuantity < 0) {
            showStatus("Quantity cannot be negative.", true);
            return;
        }

        int itemThreshold;
        try {
            itemThreshold = Integer.parseInt(thresholdField.getText().trim());
        } catch (NumberFormatException e) {
            showStatus("Invalid threshold. Please enter a whole number.", true);
            return;
        }
        if (itemThreshold < 0) {
            showStatus("Threshold cannot be negative.", true);
            return;
        }

        String itemCategory = categoryField.getText().trim();
        if (itemCategory.isEmpty()) {
            showStatus("Item category cannot be empty.", true);
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
            showStatus("Item category must contain only letters.", true);
            return;
        }

        if (datePicker.getValue() == null) {
            showStatus("Please select a purchase date.", true);
            return;
        }
        String itemDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String itemImage = imageField.getText().trim();
        if (itemImage.isEmpty()) {
            showStatus("Item image filename cannot be empty.", true);
            return;
        }

        foundItem.setItemName(itemName);
        foundItem.setItemBrand(itemBrand);
        foundItem.setItemPrice(itemPrice);
        foundItem.setItemQuantity(itemQuantity);
        foundItem.setItemCategory(itemCategory);
        foundItem.setItemDate(itemDate);
        foundItem.setItemImage(itemImage);
        foundItem.setLowStockThreshold(itemThreshold);

        if (rewriteInventoryFile()) {
            AuditLogger.log("UPDATE", foundItem.getItemCode(),itemQuantity);
            showStatus("Item updated successfully.", false);
        }

    }

    private void clearFields() {
        nameField.clear();
        brandField.clear();
        priceField.clear();
        quantityField.clear();
        categoryField.clear();
        datePicker.setValue(null);
        imageField.clear();
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setStyle(isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
        statusLabel.setText(message);
    }

    @FXML
    private void handleChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Item Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(imageField.getScene().getWindow());

        if (selectedFile != null) {
            try {
                File imagesFolder = new File("images");
                if (!imagesFolder.exists()) {
                    imagesFolder.mkdir();
                }

                File destination = new File(imagesFolder, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imageField.setText(selectedFile.getName());
            } catch (IOException e) {
                showStatus("Failed to copy image file.", true);
            }
        }
    }

    @Override
    protected void onSavedFailed() {
        showStatus("Updated in memory, but failed to save to file.", true);
    }
}
