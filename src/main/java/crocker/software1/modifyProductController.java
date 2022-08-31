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

public class modifyProductController implements Initializable {
    @FXML
    private TableView<Part> addProductAddPartTableView;

    @FXML
    private TableView<Part> addProductDeletePartTableView;

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

    }

    @FXML
    void onActionModifyProductAddPartSearchButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyProductCancelButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyProductDeletePartButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyProductSaveButton(ActionEvent event) {

    }

    @FXML
    void onActionModifyProductSearchText(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}