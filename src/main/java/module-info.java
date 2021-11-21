module com.example.portfolio3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.portfolio3 to javafx.fxml;
    exports com.example.portfolio3;
}