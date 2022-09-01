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

    private ObservableList<Part> temporaryAssociatedParts = FXCollections.observableArrayList();

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
        } else {
            temporaryAssociatedParts.add(modifyProductAddPartTableView.getSelectionModel().getSelectedItem());
            modifyProductDeletePartTableView.setItems(temporaryAssociatedParts);
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
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this part from the product?");
            alert.setTitle("Confirm Remove");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Part selectedPart = modifyProductDeletePartTableView.getSelectionModel().getSelectedItem();
                temporaryAssociatedParts.remove(selectedPart);
                modifyProductDeletePartTableView.setItems(temporaryAssociatedParts);
            }
        }
    }

    @FXML
    void onActionModifyProductSaveButton(ActionEvent event) throws IOException {
        if (!modifyProductNameText.getText().isEmpty() &&
                !modifyProductInvText.getText().isEmpty() &&
                !modifyProductPriceText.getText().isEmpty() &&
                !modifyProductMinText.getText().isEmpty() &&
                !modifyProductMaxText.getText().isEmpty())
        {
            Product newProduct = new Product(modifyProductDeletePartTableView.getItems(),
                    Integer.parseInt(modifyProductIdText.getText()),
                    modifyProductNameText.getText(), Double.parseDouble(modifyProductPriceText.getText()),
                    Integer.parseInt(modifyProductInvText.getText()),
                    Integer.parseInt(modifyProductMinText.getText()), Integer.parseInt(modifyProductMaxText.getText()));
            Product deletedProduct = Inventory.lookupProduct(Integer.parseInt(modifyProductIdText.getText()));
            Inventory.deleteProduct(deletedProduct);
            Inventory.addProduct(newProduct);
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

    @FXML
    void onActionModifyProductSearchText(ActionEvent event) {
        ObservableList<Part> searchList;
        if (!modifyProductSearchText.getText().isEmpty()) {
            for (Part part : Inventory.getAllParts()) {
                if (modifyProductSearchText.getText().equals(String.valueOf(part.getId()))) {
                    modifyProductAddPartTableView.getSelectionModel().select(part);
                    return;
                }
            }
            searchList = Inventory.lookupPart(modifyProductSearchText.getText());
            if (searchList.isEmpty()) {
                modifyProductAddPartTableView.getSelectionModel().clearSelection();
                modifyProductAddPartTableView.setItems(Inventory.getAllParts());
                Alert alert = new Alert(Alert.AlertType.ERROR, "No part was found.");
                alert.setTitle("No Part Found");
                Optional<ButtonType> result = alert.showAndWait();
                return;
            }
            modifyProductAddPartTableView.getSelectionModel().clearSelection();
            modifyProductAddPartTableView.setItems(Inventory.lookupPart(modifyProductSearchText.getText()));
            return;
        }
        modifyProductAddPartTableView.getSelectionModel().clearSelection();
        modifyProductAddPartTableView.setItems(Inventory.getAllParts());
    }

    public void displayMainProduct(Product product) {
        modifyProductDeletePartTableView.setItems(product.getAssociatedParts());
        temporaryAssociatedParts = product.getAssociatedParts();

        modifyProductDeletePartTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductDeletePartTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductDeletePartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductDeletePartTableCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        modifyProductIdText.setText(String.valueOf(product.getId()));
        modifyProductNameText.setText(product.getName());
        modifyProductInvText.setText(String.valueOf(product.getStock()));
        modifyProductPriceText.setText(String.valueOf(product.getPrice()));
        modifyProductMaxText.setText(String.valueOf(product.getMax()));
        modifyProductMinText.setText(String.valueOf(product.getMin()));
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