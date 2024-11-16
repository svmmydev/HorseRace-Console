package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class WelcomeController {

    @FXML
    private Label welcomeMessageLabel;

    @FXML
    private void initialize() {
        welcomeMessageLabel.setText("Bienvenido a la aplicación");
    }
}
