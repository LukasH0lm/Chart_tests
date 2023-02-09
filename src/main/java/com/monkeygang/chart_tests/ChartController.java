package com.monkeygang.chart_tests;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author spangsberg
 */
public class ChartController implements Initializable {

    @FXML
    private BorderPane borderPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setLeft(buildPieChart());
        borderPane.setCenter(buildPieChart2030());

    }

    @FXML
    private void handleShowBarChart() {

        borderPane.setCenter(buildBarChart());
        borderPane.setLeft(null);
    }

    @FXML
    private void handleShowPieChart() {
        borderPane.setLeft(buildPieChart());
        borderPane.setCenter(buildPieChart2030());
    }

    @FXML
    private void handleShowWeatherChart() {
        borderPane.setLeft(null);
        borderPane.setCenter(buildWeatherChart());
    }

    private BarChart buildBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("population of tønder sorted by age group");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("# of inhabitants");

        BarChart barChart2020 = new BarChart(xAxis, yAxis);


        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("population of tønder 2020");

        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("population of tønder 2030");

        dataSeries1.getData().add(new XYChart.Data("0-2", 1018));
        dataSeries1.getData().add(new XYChart.Data("3-5", 1103));
        dataSeries1.getData().add(new XYChart.Data("6-16", 4494));
        dataSeries1.getData().add(new XYChart.Data("17-19", 1422));
        dataSeries1.getData().add(new XYChart.Data("20-29", 3488));
        dataSeries1.getData().add(new XYChart.Data("30-45", 5984));
        dataSeries1.getData().add(new XYChart.Data("46-64", 10583));
        dataSeries1.getData().add(new XYChart.Data("65-79", 6966));
        dataSeries1.getData().add(new XYChart.Data("+80", 2306));

        barChart2020.getData().add(dataSeries1);

        dataSeries2.getData().add(new XYChart.Data("0-2",   994 ));
        dataSeries2.getData().add(new XYChart.Data("3-5",   1020));
        dataSeries2.getData().add(new XYChart.Data("6-16",  4052));
        dataSeries2.getData().add(new XYChart.Data("17-19", 1189));
        dataSeries2.getData().add(new XYChart.Data("20-29", 3090));
        dataSeries2.getData().add(new XYChart.Data("30-45", 5904));
        dataSeries2.getData().add(new XYChart.Data("46-64", 8619));
        dataSeries2.getData().add(new XYChart.Data("65-79", 7555));
        dataSeries2.getData().add(new XYChart.Data("+80",   3183));

        barChart2020.getData().add(dataSeries2);




        return barChart2020;


    }



    private PieChart buildPieChart() {

        //Preparing ObservbleList object
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("0-2", 1018),
                new PieChart.Data("3-5", 1103),
                new PieChart.Data("6-16", 4494),
                new PieChart.Data("17-19", 1422),
                new PieChart.Data("20-29", 3488),
                new PieChart.Data("30-45", 5984),
                new PieChart.Data("46-64", 10583),
                new PieChart.Data("65-79", 6966),
                new PieChart.Data("+80", 2306)
        );

        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart

        //attach tooltips
        createToolTips(pieChart);




        pieChart.setTitle("inhabitants in tønder 2022"); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data
        pieChart.setLabelLineLength(50); //Setting the length of the label line
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(180);

        //bind value and label on each pie slice to reflect changes
        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName())
                ));


        ContextMenu contextMenu = new ContextMenu(); //create context menu
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);


        //Add event handler to display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }
                    }
                });


        //Before Java 8
        //Add event handler to change chart type (anonymous inner class)
        miSwitchToBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                borderPane.setCenter(buildBarChart());
            }
        });


        //Java 8 and newer (lambda expression)
        miSwitchToBarChart.setOnAction(event -> { borderPane.setCenter(buildBarChart()); });


        return pieChart;
    }

    private PieChart buildPieChart2030() {

        //Preparing ObservbleList object
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("0-2",   994 ),
                new PieChart.Data("3-5",   1020),
                new PieChart.Data("6-16",  4052),
                new PieChart.Data("17-19", 1189),
                new PieChart.Data("20-29", 3090),
                new PieChart.Data("30-45", 5904),
                new PieChart.Data("46-64", 8619),
                new PieChart.Data("65-79", 7555),
                new PieChart.Data("+80",   3183)
        );

        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart

        //attach tooltips
        createToolTips(pieChart);



        pieChart.setTitle("inhabitants in tønder 2030"); //Setting the title of the Pie chart
        pieChart.setClockwise(true); //setting the direction to arrange the data
        pieChart.setLabelLineLength(50); //Setting the length of the label line
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(false);
        pieChart.setStartAngle(180);

        //bind value and label on each pie slice to reflect changes
        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName())
                ));


        ContextMenu contextMenu = new ContextMenu(); //create context menu
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        MenuItem miSwitchToWeatherChart = new MenuItem("Switch to Weather Chart");
        contextMenu.getItems().add(miSwitchToWeatherChart);


        //Add event handler to display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }
                    }
                });


        //Before Java 8
        //Add event handler to change chart type (anonymous inner class)
        miSwitchToBarChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                borderPane.setCenter(buildBarChart());
                borderPane.setLeft(null);
            }
        });

        miSwitchToWeatherChart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                borderPane.setCenter(buildWeatherChart());
                borderPane.setLeft(null);
            }
        });


        //Java 8 and newer (lambda expression)
        //miSwitchToBarChart.setOnAction(event -> { borderPane.setCenter(buildBarChart()); });


        return pieChart;
    }

    private BarChart buildWeatherChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("days");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("# of things");

        BarChart barChartWeather = new BarChart(xAxis, yAxis);


        XYChart.Series tempSeries = new XYChart.Series();
        tempSeries.setName("temperature in C");

        XYChart.Series rainChart = new XYChart.Series();
        rainChart.setName("chance of rain in %");

        tempSeries.getData().add(new XYChart.Data("Monday",   7));
        tempSeries.getData().add(new XYChart.Data("Tuesday",  6));
        tempSeries.getData().add(new XYChart.Data("Wednesday",7));
        tempSeries.getData().add(new XYChart.Data("Thursday", 7));
        tempSeries.getData().add(new XYChart.Data("Friday",   7));
        tempSeries.getData().add(new XYChart.Data("Saturday", 9));
        tempSeries.getData().add(new XYChart.Data("Sunday",   8));

        barChartWeather.getData().add(tempSeries);

        rainChart.getData().add(new XYChart.Data("Monday",    14));
        rainChart.getData().add(new XYChart.Data("Tuesday",   15));
        rainChart.getData().add(new XYChart.Data("Wednesday", 24));
        rainChart.getData().add(new XYChart.Data("Thursday",  33));
        rainChart.getData().add(new XYChart.Data("Friday",    60));
        rainChart.getData().add(new XYChart.Data("Saturday",  24));
        rainChart.getData().add(new XYChart.Data("Sunday",    39));

        barChartWeather.getData().add(rainChart);

        //set first bar color
        for(Node n:barChartWeather.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: orange;");
        }
        //second bar color
        for(Node n:barChartWeather.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: lightblue;");
        }


        return barChartWeather;

    }


    /**
     *
     */
    @FXML
    private void handleClose() {
        System.exit(0);
    }


    /**
     *
     */
    @FXML
    private void handleUpdatePieData() {
        Node node = borderPane.getCenter();

        if (node instanceof PieChart)
        {
            PieChart pc = (PieChart) node;
            double value = pc.getData().get(2).getPieValue();
            pc.getData().get(2).setPieValue(value * 1.10);
            createToolTips(pc);
        }
    }


    /**
     * Creates tooltips for all data entries
     * @param pc
     */
    private void createToolTips(PieChart pc) {

        for (PieChart.Data data: pc.getData()) {
            String msg = Double.toString(data.getPieValue());

            Tooltip tp = new Tooltip(msg);
            tp.setShowDelay(Duration.seconds(0));

            Tooltip.install(data.getNode(), tp);

            //update tooltip data when changed
            data.pieValueProperty().addListener((observable, oldValue, newValue) ->
            {
                tp.setText(newValue.toString());
            });
        }
    }
}