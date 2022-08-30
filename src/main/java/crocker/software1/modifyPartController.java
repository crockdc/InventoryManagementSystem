package crocker.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class modifyPartController implements Initializable {
    private Stage stage;
    private Parent scene;
    @FXML
    private Button modifyPartCancelButton;

    @FXML
    private Label modifyPartCompanyLabel;

    @FXML
    private TextField modifyPartCompanyText;

    @FXML
    private TextField modifyPartCostText;

    @FXML
    private TextField modifyPartIDtext;

    @FXML
    private TextField modifyPartInvText;

    @FXML
    private TextField modifyPartMaxText;

    @FXML
    private TextField modifyPartMinText;

    @FXML
    private TextField modifyPartNameText;

    @FXML
    private RadioButton modifyPartInHouseToggle;

    @FXML
    private RadioButton modifyPartOutsourcedToggle;

    @FXML
    private Button modifyPartSaveButton;

    @FXML
    private ToggleGroup modifyPartToggle;

    @FXML
    void onActionModifyPartInHouseToggle(ActionEvent event) {

    }

    @FXML
    void onActionModifyPartOutsourceToggle(ActionEvent event) {

    }


    @FXML
    void onActionModifyPartCancelButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPartSaveButton(ActionEvent event) throws IOException {
        if (!modifyPartNameText.getText().isEmpty() &&
                !modifyPartInvText.getText().isEmpty() &&
                !modifyPartCostText.getText().isEmpty() &&
                !modifyPartMinText.getText().isEmpty() &&
                !modifyPartMaxText.getText().isEmpty() &&
                !modifyPartCompanyText.getText().isEmpty()) {
            if (modifyPartInHouseToggle.isSelected()) {
                InHouse inhouse = new InHouse(Inventory.getAllParts().size() + 1, modifyPartNameText.getText(),
                        Double.parseDouble(modifyPartCostText.getText()), Integer.parseInt(modifyPartInvText.getText()),
                        Integer.parseInt(modifyPartMinText.getText()), Integer.parseInt(modifyPartMaxText.getText()),
                        Integer.parseInt(modifyPartCompanyText.getText()));
                Inventory.addPart(inhouse);
            } else {
                OutSourced outsourced = new OutSourced(Inventory.getAllParts().size(), modifyPartNameText.getText(),
                        Double.parseDouble(modifyPartCostText.getText()), Integer.parseInt(modifyPartInvText.getText()),
                        Integer.parseInt(modifyPartMinText.getText()), Integer.parseInt(modifyPartMaxText.getText()),
                        modifyPartCompanyText.getText());
                Inventory.addPart(outsourced);
            }
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please complete all fields.");
            alert.setTitle("Incomplete Fields Error");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    public void displayMainPart(Part part) {
        if (part.getClass() == InHouse.class) {
            modifyPartInHouseToggle.setSelected(true);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}