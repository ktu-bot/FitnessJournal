package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static BorderPane root;
    static CalendarApp calendarApp;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        calendarApp = new CalendarApp();
        calendarApp.loadCalendar();

        root = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        primaryStage.setTitle("Fitness Journal");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
