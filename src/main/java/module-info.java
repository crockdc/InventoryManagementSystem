module crocker.software1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens crocker.software1 to javafx.fxml;
    exports crocker.software1;
}