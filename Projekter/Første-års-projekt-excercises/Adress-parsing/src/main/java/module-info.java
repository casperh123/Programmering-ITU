module com.example.adressparsing {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.adressparsing to javafx.fxml;
    exports com.example.adressparsing;
}