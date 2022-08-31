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

public class addProductController implements Initializable {
    Stage stage;
    Parent scene;
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

    @FXML
    void onActionAddProductAddPartButton(ActionEvent event) {

    }


    @FXML
    void onActionAddProductAddPartSearchButton(ActionEvent event) {
        onActionAddProductSearchText(event);
    }

    @FXML
    void onActionAddProductCancelButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProductDeletePartButton(ActionEvent event) {

    }

    @FXML
    void onActionAddProductSaveButton(ActionEvent event) {
        int newId = 1;
        while(true) {
            int idMatchCount = 0;
            for (Product product: Inventory.getAllProducts()) {
                if (product.getId() == newId) {
                    newId = product.getId() + 1;
                    idMatchCount++;
                }
            }
            if (idMatchCount == 0) {
                break;
            }
        }
        if (!addProductNameText.getText().isEmpty() &&
                !addProductInvText.getText().isEmpty() &&
                !addProductPriceText.getText().isEmpty() &&
                !addProductMinText.getText().isEmpty() &&
                !addProductMaxText.getText().isEmpty() &&
                !addProductDeletePartTableView.getItems().isEmpty())
        {
            Product newProduct = new Product(addProductDeletePartTableView.getItems(),newId, addProductNameText.getText(),
                    Double.parseDouble(addProductPriceText.getText()), Integer.parseInt(addProductInvText.getText()),
                    Integer.parseInt(addProductMinText.getText()), Integer.parseInt(addProductMaxText.getText()));
            Inventory.addProduct(newProduct);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please complete all fields and add at least ONE associated part.");
            alert.setTitle("Incomplete Fields Error");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    void onActionAddProductSearchText(ActionEvent event) {
        ObservableList<Part> searchList = FXCollections.observableArrayList();
        if (!addProductSearchText.getText().isEmpty()) {
            for (Part part : Inventory.getAllParts()) {
                if (addProductSearchText.getText().equals(String.valueOf(part.getId()))) {
                    searchList.add(part);
                    if (!searchList.isEmpty()) {
                        addProductAddPartTableView.setItems(searchList);
                        return;
                    }
                }
            }
            addProductAddPartTableView.setItems(Inventory.lookupPart(addProductSearchText.getText()));
            return;
        }
        addProductAddPartTableView.setItems(Inventory.getAllParts());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductAddPartTableView.setItems(Inventory.getAllParts());

        addProductAddPartTablePartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductAddPartTablePartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductAddPartTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductAddPartTableCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}