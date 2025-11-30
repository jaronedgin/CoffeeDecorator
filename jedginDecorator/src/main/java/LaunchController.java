import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchController {

    @FXML private Button shotButton;
    private boolean shotAdded = false;

    @FXML private Button creamButton;
    private boolean creamAdded = false;

    @FXML private Button sugarButton;
    private boolean sugarAdded = false;

    @FXML private Button syrupButton;
    private boolean syrupAdded = false;

    @FXML private Button blendButton;
    private boolean blended = false;

    @FXML private Button resetButton;
    @FXML private Button completeButton;

    private CoffeeOrder order;

    @FXML private TextArea orderDisplay;

    @FXML
    public void initialize() {
        order = new CoffeeOrder();
        updateDisplay();
    }

    @FXML
    private void toggleShot() {
        if (!shotAdded) {
            order.addExtraShot();
            shotButton.getStyleClass().add("button-pressed");
        } else {
            order.remove(ExtraShot.class);
            shotButton.getStyleClass().remove("button-pressed");
        }
        shotAdded = !shotAdded;
        updateDisplay();
    }

    @FXML
    private void toggleCream() {
        if (!creamAdded) {
            order.addCream();
            creamButton.getStyleClass().add("button-pressed");
        } else {
            order.remove(Cream.class);
            creamButton.getStyleClass().remove("button-pressed");
        }
        creamAdded = !creamAdded;
        updateDisplay();
    }

    @FXML
    private void toggleSugar() {
        if (!sugarAdded) {
            order.addSugar();
            sugarButton.getStyleClass().add("button-pressed");
        } else {
            order.remove(Sugar.class);
            sugarButton.getStyleClass().remove("button-pressed");
        }
        sugarAdded = !sugarAdded;
        updateDisplay();
    }

    @FXML
    private void toggleSyrup() {
        if (!syrupAdded) {
            order.addSyrup();
            syrupButton.getStyleClass().add("button-pressed");
        } else {
            order.remove(Syrup.class);
            syrupButton.getStyleClass().remove("button-pressed");
        }
        syrupAdded = !syrupAdded;
        updateDisplay();
    }

    @FXML
    private void toggleBlend() {
        if (!blended) {
            order.blend();
            blendButton.getStyleClass().add("button-pressed");
        } else {
            order.remove(Blend.class);
            blendButton.getStyleClass().remove("button-pressed");
        }
        blended = !blended;
        updateDisplay();
    }

    @FXML
    private void resetOrder() {
        order.resetOrder();
        shotAdded = creamAdded = sugarAdded = syrupAdded = blended = false;
        shotButton.getStyleClass().remove("button-pressed");
        creamButton.getStyleClass().remove("button-pressed");
        sugarButton.getStyleClass().remove("button-pressed");
        syrupButton.getStyleClass().remove("button-pressed");
        blendButton.getStyleClass().remove("button-pressed");
        updateDisplay();
    }

    @FXML
    private void completeOrder() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Completed.fxml"));
        Parent root = loader.load();

        CompletedController completedController = loader.getController();

        double total = order.make();
        completedController.setTotal(total);
        completedController.setOrderDisplay(orderDisplay);

        Stage stage = (Stage) completeButton.getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
    }

    private void updateDisplay() {
        double total = order.make();

        java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
        java.io.PrintStream ps = new java.io.PrintStream(buffer);
        java.io.PrintStream old = System.out;
        System.setOut(ps);

        order.make();

        System.setOut(old);

        String details = buffer.toString();

        orderDisplay.setText(details + "\nTotal: $" + String.format("%.2f", total));
    }
}

