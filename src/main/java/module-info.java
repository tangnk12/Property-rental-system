module com.example.property {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.example.property to javafx.fxml;
    exports com.example.property;
}