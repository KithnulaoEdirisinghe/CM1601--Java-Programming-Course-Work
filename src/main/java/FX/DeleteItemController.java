package FX;

import com.example.cm1601_cw.InventoryItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DeleteItemController extends InventoryWriteController {

    @FXML private TextField findCodeField;
    @FXML private Label detailsLabel;
    @FXML private Label statusLabel;
    @FXML private Button deleteButton;

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
            detailsLabel.setText("");
            deleteButton.setDisable(true);
            showStatus("Item not found.", true);
            return;
        }

        detailsLabel.setText(foundItem.getItemCode() + " - " + foundItem.getItemName() + " (" + foundItem.getItemBrand() + ")");
        deleteButton.setDisable(false);
        showStatus("Item found. Click delete to remove it.", false);
    }

    @FXML
    private void handleDelete() {
        if (foundItem == null) {
            showStatus("Find an item first.", true);
            return;
        }

        inventory.remove(foundItem);
        if (rewriteInventoryFile()) {
            showStatus("Item deleted successfully.", false);
        }
        
        detailsLabel.setText("");
        findCodeField.clear();
        deleteButton.setDisable(true);
        foundItem = null;
    }

    private void showStatus(String message, boolean isError) {
        statusLabel.setStyle(isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
        statusLabel.setText(message);
    }

    @Override
    protected void onSavedFailed() {
        showStatus("Deleted in memory, but failed to save to file.",true);
    }
}
