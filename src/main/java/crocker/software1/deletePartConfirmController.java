package crocker.software1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class deletePartConfirmController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}