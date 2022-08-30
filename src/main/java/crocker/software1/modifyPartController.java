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
        modifyPartInHouseToggle.setSelected(true);
        modifyPartCompanyLabel.setText("Machine ID");
        modifyPartCompanyText.setPromptText("Machine ID");
    }

    @FXML
    void onActionModifyPartOutsourceToggle(ActionEvent event) {
        modifyPartOutsourcedToggle.setSelected(true);
        modifyPartCompanyLabel.setText("Company Name");
        modifyPartCompanyText.setPromptText("Company Name");
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
                InHouse inhouse = new InHouse(Integer.parseInt(modifyPartIDtext.getText()), modifyPartNameText.getText(),
                        Double.parseDouble(modifyPartCostText.getText()), Integer.parseInt(modifyPartInvText.getText()),
                        Integer.parseInt(modifyPartMinText.getText()), Integer.parseInt(modifyPartMaxText.getText()),
                        Integer.parseInt(modifyPartCompanyText.getText()));
                Part deletedPart = Inventory.lookUpPart(Integer.parseInt(modifyPartIDtext.getText()));
                Inventory.deletePart(deletedPart);
                Inventory.addPart(inhouse);
            } else {
                OutSourced outsourced = new OutSourced(Integer.parseInt(modifyPartIDtext.getText()), modifyPartNameText.getText(),
                        Double.parseDouble(modifyPartCostText.getText()), Integer.parseInt(modifyPartInvText.getText()),
                        Integer.parseInt(modifyPartMinText.getText()), Integer.parseInt(modifyPartMaxText.getText()),
                        modifyPartCompanyText.getText());
                Part deletedPart = Inventory.lookUpPart(Integer.parseInt(modifyPartIDtext.getText()));
                Inventory.deletePart(deletedPart);
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
            onActionModifyPartInHouseToggle(new ActionEvent());
            modifyPartCompanyText.setText(String.valueOf(((InHouse) part).getMachineId()));
        } else {
            onActionModifyPartOutsourceToggle(new ActionEvent());
            modifyPartCompanyText.setText(((OutSourced) part).getCompanyName());
        }
        modifyPartIDtext.setText(String.valueOf(part.getId()));
        modifyPartNameText.setText(part.getName());
        modifyPartInvText.setText(String.valueOf(part.getStock()));
        modifyPartCostText.setText(String.valueOf(part.getPrice()));
        modifyPartMaxText.setText(String.valueOf(part.getMax()));
        modifyPartMinText.setText(String.valueOf(part.getMin()));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}