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
import java.util.Scanner;

public class addPartController implements Initializable {
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

    private boolean verifyDouble(String string) {
        Scanner scanner = new Scanner(string);
        if (scanner.hasNextDouble()) {
            return true;
        }
        return false;
    }

    private boolean verifyInteger(String string) {
        Scanner scanner = new Scanner(string);
        if (scanner.hasNextInt()) {
            return true;
        }
        return false;
    }

    @FXML
    void onActionAddPartCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel adding a new part?");
        alert.setTitle("Confirm Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionAddPartCompanyText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartInHouse(ActionEvent event) {
        addPartCompanyLabel.setText("Machine ID");
        addPartCompanyText.setPromptText("Machine ID");
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
        addPartCompanyLabel.setText("Company Name");
        addPartCompanyText.setPromptText("Company Name");
    }

    @FXML
    void onActionAddPartPriceText(ActionEvent event) {

    }

    @FXML
    void onActionAddPartSaveButton(ActionEvent event) throws IOException {
        int newId = 1;
        while (true) {
            int idMatchCount = 0;
            for (Part part : Inventory.getAllParts()) {
                if (part.getId() == newId) {
                    newId = part.getId() + 1;
                    idMatchCount++;
                }
            }
            if (idMatchCount == 0) {
                break;
            }
        }
        try {
            if (!addPartNameText.getText().isEmpty() &&
                    !addPartInvText.getText().isEmpty() &&
                    !addPartPriceText.getText().isEmpty() &&
                    !addPartMinText.getText().isEmpty() &&
                    !addPartMaxText.getText().isEmpty() &&
                    !addPartCompanyText.getText().isEmpty()) {
                if (Integer.parseInt(addPartMaxText.getText()) > Integer.parseInt(addPartMinText.getText())) {
                    if (Integer.parseInt(addPartInvText.getText()) >= Integer.parseInt(addPartMinText.getText())
                            && Integer.parseInt(addPartInvText.getText()) <= Integer.parseInt(addPartMaxText.getText())) {
                        if (addPartInHouse.isSelected()) {
                            InHouse inhouse = new InHouse(newId, addPartNameText.getText(),
                                    Double.parseDouble(addPartPriceText.getText()), Integer.parseInt(addPartInvText.getText()),
                                    Integer.parseInt(addPartMinText.getText()), Integer.parseInt(addPartMaxText.getText()),
                                    Integer.parseInt(addPartCompanyText.getText()));
                            Inventory.addPart(inhouse);
                        } else {
                            OutSourced outsourced = new OutSourced(newId, addPartNameText.getText(),
                                    Double.parseDouble(addPartPriceText.getText()), Integer.parseInt(addPartInvText.getText()),
                                    Integer.parseInt(addPartMinText.getText()), Integer.parseInt(addPartMaxText.getText()),
                                    addPartCompanyText.getText());
                            Inventory.addPart(outsourced);
                        }
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                        stage.setTitle("Inventory Management System");
                        stage.setScene(new Scene(scene));
                        stage.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be between Min and Max values.");
                        alert.setTitle("Inventory Error");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Min field must be less than Max field.");
                    alert.setTitle("Min/Max Error");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please complete all fields.");
                alert.setTitle("Incomplete Fields Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
       } catch (NumberFormatException e) {
            if (addPartInHouse.isSelected()) {
                if (!verifyInteger(addPartCompanyText.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID field displays \"" + addPartCompanyText.getText()
                    + "\"." + "\nPlease enter numerical value only.");
                    alert.setTitle("Machine ID Field Error");
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }
            if (!verifyInteger(addPartInvText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory field displays \"" +
                        addPartInvText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Inventory Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if (!verifyDouble(addPartPriceText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price/Cost field displays \"" +
                        addPartPriceText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Price/Cost Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if (!verifyInteger(addPartMaxText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max field displays \"" +
                        addPartMaxText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Max Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if (!verifyInteger(addPartMinText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min field displays \"" +
                        addPartMinText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Min Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}