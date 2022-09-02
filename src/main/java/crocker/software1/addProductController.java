package crocker.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class addProductController implements Initializable {
    Stage stage;
    Parent scene;

    private ObservableList<Part> temporaryAssociatedParts = FXCollections.observableArrayList();
    @FXML
    private Button addProductAddPartButton;

    @FXML
    private Button addProductAddPartSearchButton;

    @FXML
    private TableColumn<Part, Double> addProductAddPartTableCostColumn;

    @FXML
    private TableColumn<Part, Integer> addProductAddPartTableInventoryColumn;

    @FXML
    private TableColumn<Part, Integer> addProductAddPartTablePartIdColumn;

    @FXML
    private TableColumn<Part, String> addProductAddPartTablePartNameColumn;

    @FXML
    private TableView<Part> addProductAddPartTableView;

    @FXML
    private Button addProductCancelButton;

    @FXML
    private Button addProductDeletePartButton;

    @FXML
    private TableColumn<Part, Double> addProductDeletePartTableCostColumn;

    @FXML
    private TableColumn<Part, Integer> addProductDeletePartTableInventoryColumn;

    @FXML
    private TableColumn<Part, Integer> addProductDeletePartTablePartIdColumn;

    @FXML
    private TableColumn<Part, String> addProductDeletePartTablePartNameColumn;

    @FXML
    private TableView<Part> addProductDeletePartTableView;

    @FXML
    private TextField addProductIdText;

    @FXML
    private TextField addProductInvText;

    @FXML
    private TextField addProductMaxText;

    @FXML
    private TextField addProductMinText;

    @FXML
    private TextField addProductNameText;

    @FXML
    private TextField addProductPriceText;

    @FXML
    private Button addProductSaveButton;

    @FXML
    private TextField addProductSearchText;

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
    void onActionAddProductAddPartButton(ActionEvent event) {
        if (addProductAddPartTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to associate with the product.");
            alert.setTitle("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            temporaryAssociatedParts.add(addProductAddPartTableView.getSelectionModel().getSelectedItem());
            addProductDeletePartTableView.setItems(temporaryAssociatedParts);
        }
    }


    @FXML
    void onActionAddProductAddPartSearchButton(ActionEvent event) {
        onActionAddProductSearchText(event);
    }

    @FXML
    void onActionAddProductCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel adding a new product?");
        alert.setTitle("Confirm Cancel");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage.setTitle("Inventory Management System");
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionAddProductDeletePartButton(ActionEvent event) {
        if (addProductDeletePartTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an associated part to remove.");
            alert.setTitle("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this part from the product?");
            alert.setTitle("Confirm Remove");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Part selectedPart = addProductDeletePartTableView.getSelectionModel().getSelectedItem();
                temporaryAssociatedParts.remove(selectedPart);
                addProductDeletePartTableView.setItems(temporaryAssociatedParts);
            }
        }
    }

    @FXML
    void onActionAddProductSaveButton(ActionEvent event) throws IOException {
        int newId = 1;
        while (true) {
            int idMatchCount = 0;
            for (Product product : Inventory.getAllProducts()) {
                if (product.getId() == newId) {
                    newId = product.getId() + 1;
                    idMatchCount++;
                }
            }
            if (idMatchCount == 0) {
                break;
            }
        }
        try {
            if (!addProductNameText.getText().isEmpty() &&
                    !addProductInvText.getText().isEmpty() &&
                    !addProductPriceText.getText().isEmpty() &&
                    !addProductMinText.getText().isEmpty() &&
                    !addProductMaxText.getText().isEmpty()) {
                if (Integer.parseInt(addProductMaxText.getText()) > Integer.parseInt(addProductMinText.getText())) {
                    if (Integer.parseInt(addProductInvText.getText()) >= Integer.parseInt(addProductMinText.getText())
                            && Integer.parseInt(addProductInvText.getText()) <= Integer.parseInt(addProductMaxText.getText())) {
                        Product newProduct = new Product(addProductDeletePartTableView.getItems(), newId, addProductNameText.getText(),
                                Double.parseDouble(addProductPriceText.getText()), Integer.parseInt(addProductInvText.getText()),
                                Integer.parseInt(addProductMinText.getText()), Integer.parseInt(addProductMaxText.getText()));
                        Inventory.addProduct(newProduct);
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
            if (!verifyInteger(addProductInvText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory field displays \"" +
                        addProductInvText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Inventory Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if (!verifyDouble(addProductPriceText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price/Cost field displays \"" +
                        addProductPriceText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Price/Cost Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if (!verifyInteger(addProductMaxText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Max field displays \"" +
                        addProductMaxText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Max Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
            if (!verifyInteger(addProductMinText.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min field displays \"" +
                        addProductMinText.getText() + "\"." + "\nPlease enter numerical value only.");
                alert.setTitle("Min Field Error");
                Optional<ButtonType> result = alert.showAndWait();
            }
        }
    }

    @FXML
    void onActionAddProductSearchText(ActionEvent event) {
        ObservableList<Part> searchList;
        if (!addProductSearchText.getText().isEmpty()) {
            for (Part part : Inventory.getAllParts()) {
                if (addProductSearchText.getText().equals(String.valueOf(part.getId()))) {
                    addProductAddPartTableView.getSelectionModel().select(part);
                    return;
                }
            }
            searchList = Inventory.lookupPart(addProductSearchText.getText());
            if (searchList.isEmpty()) {
                addProductAddPartTableView.getSelectionModel().clearSelection();
                addProductAddPartTableView.setItems(Inventory.getAllParts());
                Alert alert = new Alert(Alert.AlertType.ERROR, "No part was found.");
                alert.setTitle("No Part Found");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            addProductAddPartTableView.getSelectionModel().clearSelection();
            addProductAddPartTableView.setItems(Inventory.lookupPart(addProductSearchText.getText()));
            return;
        }
        addProductAddPartTableView.getSelectionModel().clearSelection();
        addProductAddPartTableView.setItems(Inventory.getAllParts());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductAddPartTableView.setItems(Inventory.getAllParts());

        addProductAddPartTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductAddPartTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAddPartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductAddPartTableCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProductDeletePartTableView.setItems(temporaryAssociatedParts);

        addProductDeletePartTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductDeletePartTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductDeletePartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductDeletePartTableCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}