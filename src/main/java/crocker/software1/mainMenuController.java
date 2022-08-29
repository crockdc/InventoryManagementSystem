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

public class mainMenuController implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private Button mainExitButton;

    @FXML
    private TableColumn<?, ?> mainPartIDColumn;

    @FXML
    private TableColumn<?, ?> mainPartInventoryColumn;

    @FXML
    private TableColumn<?, ?> mainPartNameColumn;

    @FXML
    private TableColumn<?, ?> mainPartPriceColumn;

    @FXML
    private Button mainPartSearchButton;

    @FXML
    private TextField mainPartSearchText;

    @FXML
    private Button mainPartsAddButton;

    @FXML
    private Button mainPartsDeleteButton;

    @FXML
    private Button mainPartsModifyButton;

    @FXML
    private TableColumn<?, ?> mainProductIDColumn;

    @FXML
    private TableColumn<?, ?> mainProductInventoryColumn;

    @FXML
    private TableColumn<?, ?> mainProductNameColumn;

    @FXML
    private TableColumn<?, ?> mainProductPriceColumn;

    @FXML
    private Button mainProductSearchButton;

    @FXML
    private TextField mainProductSearchText;

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
        alert.setTitle("Confirm Part Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //System.exit(0);
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();;
    }

    @FXML
    void onActionMainDeleteProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
        alert.setTitle("Confirm Product Deletion");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //System.exit(0);
        }
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();;
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

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
        stage.setTitle("Modify Part");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionMainModifyProduct(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
        stage.setTitle("Modify Product");
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionMainSearchParts(ActionEvent event) {
        System.out.println("Works");
    }

    @FXML
    void onActionMainSearchProducts(ActionEvent event) {
        System.out.println("Works");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized!");
    }
}