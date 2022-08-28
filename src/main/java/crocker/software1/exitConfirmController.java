package crocker.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class exitConfirmController {
    Parent scene;
    Stage stage;
    @FXML
    private Button exitConfirmCancelButton;

    @FXML
    private Button exitConfirmExitButton;

    @FXML
    void onActionExitConfirmCancelButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionExitConfirmExitButton(ActionEvent event) {
        System.exit(0);
    }

}