package FX;

import com.example.cm1601_cw.CleanRead;
import com.example.cm1601_cw.DealerReadFile;
import com.example.cm1601_cw.InventoryReadFile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if (!new File("inventory_cleaned.txt").exists()) {
            new InventoryReadFile();
        }

        if (!new File("dealers_cleaned.txt").exists()) {
            new DealerReadFile();
        }

        CleanRead cleanreader = new CleanRead();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/example/cm1601_cw/main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        MainController controller = fxmlLoader.getController();
        controller.setInventory(cleanreader.getInventory());

        stage.setTitle("Malabe Tuk-Tuk & Three-Wheeler Spares Depot");
        stage.setScene(scene);
        stage.show();
    }
}
