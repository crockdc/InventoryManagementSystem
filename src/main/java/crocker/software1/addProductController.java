package crocker.software1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class addProductController implements Initializable {
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

    }

    @FXML
    void onActionAddProductCancelButton(ActionEvent event) {

    }

    @FXML
    void onActionAddProductDeletePartButton(ActionEvent event) {

    }

    @FXML
    void onActionAddProductSaveButton(ActionEvent event) {

    }

    @FXML
    void onActionAddProductSearchText(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}