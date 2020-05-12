package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static ui.Main.calendarApp;

public class Controller implements Initializable {

    @FXML
    LineChart<String, Number> chart;

    public Button resetButton;
    public Button addDay;
    public Button saveButton;
    public Button editCalendar;
    public Button viewCalendar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calendarApp = new CalendarApp();
        calendarApp.loadCalendar();
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data = GuiInputs.graphData(calendarApp);
        chart.getData().add(data);

    }

    public void resetButton() {
        if (GuiInputs.newCalendar(calendarApp)) {
            calendarApp.newCalendar();
        }
    }

    public void addDay() {
        Main.root.setCenter(GuiInputs.newDay(calendarApp));
    }

    public void saveButton() {
        calendarApp.saveCalendar();
        GuiInputs.confirmationTemplate("Your calendar has been saved");
    }

    public void editCalendar() {
        Main.root.setCenter(GuiInputs.showJournal(calendarApp));
    }

    public void viewCalendar() {
        calendarApp.saveCalendar();
        calendarApp.loadCalendar();
        XYChart.Series<String, Number> data;
        data = GuiInputs.graphData(calendarApp);
        chart.getData().clear();
        chart.getData().add(data);
        Main.root.setCenter(chart);
    }
}
