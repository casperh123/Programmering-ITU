package com.example.adressparsing;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdressParserController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}