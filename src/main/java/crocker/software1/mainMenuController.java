package crocker.software1;

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


public class mainMenuController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private Button mainExitButton;

    @FXML
    private TableColumn<Part, Integer> mainPartIDColumn;

    @FXML
    private TableColumn<Part, Integer> mainPartInventoryColumn;

    @FXML
    private TableColumn<Part, String> mainPartNameColumn;

    @FXML
    private TableColumn<Part, Double> mainPartPriceColumn;

    @FXML
    private TextField mainPartSearchText;

    @FXML
    private TableView<Part> mainPartTable;

    @FXML
    private Button mainPartsAddButton;

    @FXML
    private Button mainPartsDeleteButton;

    @FXML
    private Button mainPartsModifyButton;

    @FXML
    private TableColumn<Product, Integer> mainProductIDColumn;

    @FXML
    private TableColumn<Product, Integer> mainProductInventoryColumn;

    @FXML
    private TableColumn<Product, String> mainProductNameColumn;

    @FXML
    private TableColumn<Product, Double> mainProductPriceColumn;

    @FXML
    private TextField mainProductSearchText;

    @FXML
    private TableView<Product> mainProductTable;

    @FXML
    private Button mainProductsAddButton;

    @FXML
    private Button mainProductsDeleteButton;

    @FXML
    private Button mainProductsModifyButton;

    @FXML
    void onActionMainAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addPart.fxml"));
        stage.setTitle("Add Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionMainAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        stage.setTitle("Add Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionMainDeletePart(ActionEvent event) throws IOException {
        if (!mainPartTable.getSelectionModel().getSelectedCells().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
            alert.setTitle("Confirm Part Deletion");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to delete.");
            alert.setTitle("No Part Selected Error");
            Optional<ButtonType> result = alert.showAndWait();
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();;
    }

    @FXML
    void onActionMainDeleteProduct(ActionEvent event) throws IOException {
        if (!mainProductTable.getSelectionModel().getSelectedCells().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
            alert.setTitle("Confirm Product Deletion");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to delete.");
            alert.setTitle("No Product Selected Error");
            Optional<ButtonType> result = alert.showAndWait();
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionMainExit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.setTitle("Confirm Exit");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();;
    }

    @FXML
    void onActionMainModifyPart(ActionEvent event) throws IOException {

        if (!mainPartTable.getSelectionModel().getSelectedCells().isEmpty()) {
            Part selectedPart = mainPartTable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
            scene = loader.load();
            modifyPartController modifyPartController1 = loader.getController();
            modifyPartController1.displayMainPart(selectedPart);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            //scene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
            stage.setTitle("Modify Part");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part to modify.");
            alert.setTitle("No Part Selected Error");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    void onActionMainModifyProduct(ActionEvent event) throws IOException {
        if (!mainProductTable.getSelectionModel().getSelectedCells().isEmpty()) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product to modify.");
            alert.setTitle("No Product Selected Error");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    void onActionMainPartSearchText(ActionEvent event) {

    }

    @FXML
    void onActionMainProductSearchText(ActionEvent event) {

    }
    private static boolean firstTime = true;
    private void addTestData() {
        if (!firstTime) {
            return;
        }
        firstTime = false;
        InHouse newPart1 = new InHouse(1, "Screwdriver", 1.99, 3, 1, 5, 04332);
        Inventory.addPart(newPart1);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addTestData();

        mainPartTable.setItems(Inventory.getAllParts());

        mainPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


        System.out.println("Initialized!");

        Part selectedPart = mainPartTable.getSelectionModel().getSelectedItem();

    }
}