package FX;

import com.example.cm1601_cw.AuditLogger;
import com.example.cm1601_cw.Cart;
import com.example.cm1601_cw.CartItem;
import com.example.cm1601_cw.DiscountCalculator;
import com.example.cm1601_cw.InventoryItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckoutController extends InventoryWriteController {

    @FXML private TextField codeField;
    @FXML private TextField quantityField;
    @FXML private Label errorLabel;

    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> cartCode;
    @FXML private TableColumn<CartItem, String> cartName;
    @FXML private TableColumn<CartItem, String> cartBrand;
    @FXML private TableColumn<CartItem, Double> cartPrice;
    @FXML private TableColumn<CartItem, Integer> cartQuantity;
    @FXML private TableColumn<CartItem, Double> cartSubtotal;

    @FXML private Label totalLabel;

    private Cart cart = new Cart();

    @FXML
    public void initialize() {

        cartCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        cartName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        cartBrand.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
        cartPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        cartQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cartSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
    }

    @FXML
    public void handleAddToCart() {

        String code = codeField.getText().trim().toUpperCase();

        InventoryItem found = null;
        for (InventoryItem item : inventory) {
            if (item.getItemCode().equals(code)) {
                found = item;
                break;
            }
        }

        if (found == null) {
            errorLabel.setText("Item not found.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText().trim());
        }
        catch (NumberFormatException e) {
            errorLabel.setText("Invalid quantity.");
            return;
        }

        if (!cart.addItem(found, quantity)) {
            errorLabel.setText("Invalid quantity or not enough stock.");
            return;
        }

        refreshCart();
        codeField.clear();
        quantityField.clear();
        errorLabel.setText("Item added to cart.");
    }

    @FXML
    public void handleCheckout() {
        if (cart.isEmpty()) {
            errorLabel.setText("Cart is empty.");
            return;
        }

        for (CartItem cartItem : cart.getItems()) {
            InventoryItem item = cartItem.getItem();
            item.setItemQuantity(item.getItemQuantity() - cartItem.getQuantity());
        }

        if (!rewriteInventoryFile()) {
            return;
        }

        for (CartItem cartItem : cart.getItems()) {
            AuditLogger.log("CHECKOUT", cartItem.getItem().getItemCode(), cartItem.getQuantity());
        }

        cart.clear();
        refreshCart();
        errorLabel.setText("Checkout successful.");
    }

    private void refreshCart() {
        cartTable.setItems(FXCollections.observableArrayList(cart.getItems()));
        totalLabel.setText(String.format("%.2f", DiscountCalculator.calculateTotal(cart.getItems())));
    }

    @Override
    protected void onSavedFailed() {
        errorLabel.setText("Checkout complete in memory, but failed to save to text file.");
    }
}
