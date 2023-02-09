package com.monkeygang.chart_tests;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChartController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}