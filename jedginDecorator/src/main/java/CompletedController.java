import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class CompletedController {
    @FXML private Button newOrderButton;
    @FXML private Label totalLabel;
    @FXML private TextArea detailsArea;

    private double total;

    public void setTotal(double total) {
        this.total = total;
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    public void setOrderDisplay(TextArea orderDisplay) {
        detailsArea.setText(orderDisplay.getText());
    }

    @FXML
    private void newOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Launch.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) totalLabel.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
    }
}
