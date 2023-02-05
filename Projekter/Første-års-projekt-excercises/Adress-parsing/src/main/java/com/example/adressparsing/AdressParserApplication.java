package com.example.adressparsing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdressParserApplication extends Application {

    public String getGreeting() {
        return "Hello World";
    }


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Address Parser");

        BorderPane pane = new BorderPane();
        TextField input = new TextField();
        TextArea outputField = new TextArea();

        pane.setTop(input);
        pane.setCenter(outputField);

        input.setOnAction(e -> {
            String inputString = input.getText();
            var add = Address.parse(inputString);
            outputField.setText(add.toString());
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}