module com.monkeygang.chart_tests {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.monkeygang.chart_tests to javafx.fxml;
    exports com.monkeygang.chart_tests;
}