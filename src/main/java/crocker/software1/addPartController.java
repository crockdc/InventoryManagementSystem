package crocker.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class addPartController {
    Stage stage;
    Parent scene;
    @FXML
    private Button addPartCancelButton;

    @FXML
    private Label addPartCompanyLabel;

    @FXML
    private TextField addPartCompanyText;

    @FXML
    private TextField addPartIDtext;

    @FXML
    private RadioButton addPartInHouse;

    @FXML
    private TextField addPartInvText;

    @FXML
    private TextField addPartMaxText;

    @FXML
    private TextField addPartMinText;

    @FXML
    private TextField addPartNameText;

    @FXML
    private RadioButton addPartOutsourced;

    @FXML
    private TextField addPartPriceText;

    @FXML
    private Button addPartSaveButton;

    @FXML
    private ToggleGroup addPartToggle;

    @FXML
    void onActionAddPartCancelButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddPartCompanyText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartInHouse(ActionEvent event) {

    }

    @FXML
    void onActionAddPartInvText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartMaxText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartMinText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartNameText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartOutsourced(ActionEvent event) {

    }

    @FXML
    void onActionAddPartPriceText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartSaveButton(ActionEvent event) {

    }
}