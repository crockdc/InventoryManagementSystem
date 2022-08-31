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

public class modifyProductController implements Initializable {

    Parent scene;
    Stage stage;
    @FXML
    private TableView<Part> modifyProductAddPartTableView;

    @FXML
    private TableView<Part> modifyProductDeletePartTableView;

    @FXML
    private Button modifyProductAddPartButton;

    @FXML
    private Button modifyProductAddPartSearchButton;

    @FXML
    private TableColumn<Part, Double> modifyProductAddPartTableCostColumn;

    @FXML
    private TableColumn<Part, Integer> modifyProductAddPartTableInventoryColumn;

    @FXML
    private TableColumn<Part, Integer> modifyProductAddPartTablePartIdColumn;

    @FXML
    private TableColumn<Part, String> modifyProductAddPartTablePartNameColumn;

    @FXML
    private Button modifyProductCancelButton;

    @FXML
    private Button modifyProductDeletePartButton;

    @FXML
    private TableColumn<Part, Double> modifyProductDeletePartTableCostColumn;

    @FXML
    private TableColumn<Part, Integer> modifyProductDeletePartTableInventoryColumn;

    @FXML
    private TableColumn<Part, Integer> modifyProductDeletePartTablePartIdColumn;

    @FXML
    private TableColumn<Part, String> modifyProductDeletePartTablePartNameColumn;

    @FXML
    private TextField modifyProductIdText;

    @FXML
    private TextField modifyProductInvText;

    @FXML
    private TextField modifyProductMaxText;

    @FXML
    private TextField modifyProductMinText;

    @FXML
    private TextField modifyProductNameText;

    @FXML
    private TextField modifyProductPriceText;

    @FXML
    private Button modifyProductSaveButton;

    @FXML
    private TextField modifyProductSearchText;

    @FXML
    void onActionModifyProductAddPartButton(ActionEvent event) {
        if (modifyProductAddPartTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to associate with the product.");
            alert.setTitle("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    void onActionModifyProductAddPartSearchButton(ActionEvent event) {
        onActionModifyProductSearchText(event);
    }

    @FXML
    void onActionModifyProductCancelButton(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel product modification?");
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
    void onActionModifyProductDeletePartButton(ActionEvent event) {
        if (modifyProductDeletePartTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an associated part to remove.");
            alert.setTitle("No part selected");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    void onActionModifyProductSaveButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyProductSearchText(ActionEvent event) {
        ObservableList<Part> searchList = FXCollections.observableArrayList();
        if (!modifyProductSearchText.getText().isEmpty()) {
            for (Part part : Inventory.getAllParts()) {
                if (modifyProductSearchText.getText().equals(String.valueOf(part.getId()))) {
                    searchList.add(part);
                    if (!searchList.isEmpty()) {
                        modifyProductAddPartTableView.setItems(searchList);
                        return;
                    }
                }
            }
            modifyProductAddPartTableView.setItems(Inventory.lookupPart(modifyProductSearchText.getText()));
            return;
        }
        modifyProductAddPartTableView.setItems(Inventory.getAllParts());
    }

    public void displayMainProduct(Product product) {
        modifyProductDeletePartTableView.setItems(product.getAssociatedParts());

        modifyProductDeletePartTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductDeletePartTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductDeletePartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductDeletePartTableCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProductAddPartTableView.setItems(Inventory.getAllParts());

        modifyProductAddPartTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductAddPartTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductAddPartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductAddPartTableCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}