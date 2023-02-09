package com.monkeygang.chart_tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ChartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChartApplication.class.getResource("chart-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 480);
        stage.setTitle("Chart Test");
        stage.getIcons().add(new Image(Objects.requireNonNull(ChartApplication.class.getResourceAsStream("/com/monkeygang/chart_tests/pieChart.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}