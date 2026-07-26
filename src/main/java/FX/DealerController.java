package FX;

import com.example.cm1601_cw.Dealer;
import com.example.cm1601_cw.DealerSelector;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DealerController {

    @FXML private TableView<Dealer> dealerTable;
    @FXML private TableColumn<Dealer, String> dealerCode;
    @FXML private TableColumn<Dealer, String> dealerName;
    @FXML private TableColumn<Dealer, String> dealerPhone;
    @FXML private TableColumn<Dealer, String> dealerLocation;

    private List<Dealer> allDealers;

    @FXML
    public void initialize(){
        dealerCode.setCellValueFactory(new PropertyValueFactory<>("dealerCode"));
        dealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        dealerPhone.setCellValueFactory(new PropertyValueFactory<>("dealerPhone"));
        dealerLocation.setCellValueFactory(new PropertyValueFactory<>("dealerLocation"));
    }

    public void setDealers(List<Dealer> allDealers) {
        this.allDealers = allDealers;
        refreshTable();
    }

    @FXML
    private void handleRefresh() {
        refreshTable();
    }

    private void refreshTable(){
        List<Dealer> selected = DealerSelector.randomDealers(allDealers, 4);
        dealerTable.setItems (FXCollections.observableList(selected));
    }
}
