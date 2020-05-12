package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Day;
import model.Food;
import model.Workout;

import java.util.ArrayList;
import java.util.List;

public class GuiInputs extends Main {
    static boolean answer;
    public static Stage window0 = new Stage();
    static Stage window1 = new Stage();
    static Stage window3 = new Stage();

    public static HBox newDay(CalendarApp calendarApp) {
        Workout workout = new Workout();
        List<Food> meal = new ArrayList<>();
        Day day = new Day("", workout,meal);
        HBox hbox = new HBox();
        VBox vbox1 = toKeepMethodUnder31();
        VBox vbox2 = toKeepMethodUnder32(calendarApp, day);

        vbox1.setSpacing(20);
        vbox2.setSpacing(10);
        hbox.setSpacing(10);
        hbox.getChildren().addAll(vbox1, vbox2);
        return hbox;
    }

    private static VBox toKeepMethodUnder32(CalendarApp calendarApp, Day day) {
        VBox vbox = new VBox();
        TextField dateInput = new TextField();
        dateInput.setPromptText("1/1");
        Button addWorkout = new Button("View/Add Exercise");
        addWorkout.setOnAction(e -> addExercise(day));
        Button addFood = new Button("Add Macros:");
        addFood.setOnAction(e -> newFood(day));

        Button confirmAddDay = new Button("Add to calendar");
        confirmAddDay.setOnAction(e -> {
            day.setDate(dateInput.getText());
            calendarApp.calendar.newDay(day);
            confirmationTemplate("Date added to calendar");
        });
        vbox.getChildren().addAll(dateInput,addWorkout,addFood,confirmAddDay);
        return vbox;
    }

    private static VBox toKeepMethodUnder31() {
        VBox vbox = new VBox();
        Label dateLabel = new Label("Date:");
        Label workoutLabel = new Label("Workout:");
        Label newFood = new Label("Macros");
        vbox.getChildren().addAll(dateLabel,workoutLabel,newFood);

        return vbox;
    }

    public static void confirmationTemplate(String labelText) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Calendar Updated");
        window.setMinWidth(300);
        window.setMinHeight(125);
        Label label = new Label();
        label.setText(labelText);

        //Create two buttons
        Button yesButton = new Button("Ok");

        yesButton.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(20);


        layout.getChildren().addAll(label,yesButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void newFood(Day day) {
        HBox hbox = new HBox();
        VBox vbox = toKeepMethodUnder29();
        VBox vbox1 = toKeepMethodUnder30(day);
        vbox.setSpacing(20);
        vbox1.setSpacing(10);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10, 10,10,20));
        window3.setTitle("Food Entry");
        hbox.getChildren().addAll(vbox,vbox1);
        Scene scene = new Scene(hbox, 300, 200);
        window3.setScene(scene);
        window3.show();
    }

    private static VBox toKeepMethodUnder30(Day day) {
        VBox vbox = new VBox();

        TextField calorieInput = new TextField();
        TextField proteinInput = new TextField();
        TextField carbInput = new TextField();
        TextField fatInput = new TextField();
        Button makeDay = new Button("Finish");
        GridPane.setConstraints(makeDay, 0, 4);
        makeDay.setOnAction(e -> {
            day.newFood(calendarApp.enterFood(calorieInput.getText(),
                    carbInput.getText(), proteinInput.getText(), fatInput.getText()));
            window3.close();
        });
        vbox.getChildren().addAll(calorieInput,proteinInput,carbInput,fatInput,makeDay);
        return vbox;
    }

    private static VBox toKeepMethodUnder29() {
        VBox vbox = new VBox();

        Label calories = new Label("Calories:");

        Label protein = new Label("Protein:");

        Label carb = new Label("Carbohydrate:");

        Label fats = new Label("Fat:");

        vbox.getChildren().addAll(calories,carb,fats,protein);
        return vbox;
    }


    //Enter exercise inputs here
    private static void addExercise(Day day) {
        HBox hbox2 = new HBox();
        VBox vbox3 = toKeepMethodUnder25();
        VBox vbox4 = toKeepMethodUnder26(day);
        vbox3.setSpacing(20);
        vbox4.setSpacing(10);
        hbox2.setSpacing(10);
        hbox2.setPadding(new Insets(10, 10,10,20));
        window1.setTitle("Exercise Entry");
        hbox2.getChildren().addAll(vbox3,vbox4);
        window1.setScene(new Scene(hbox2, 300, 200));
        window1.show();
    }

    private static VBox toKeepMethodUnder26(Day day) {
        VBox vbox = new VBox();

        TextField nameInput = new TextField();

        TextField setInput = new TextField();

        TextField repInput = new TextField();

        TextField weightInput = new TextField();

        Button makeExercise = new Button("Finish");
        makeExercise.setOnAction(e -> {
            List<Food> meals = new ArrayList<>();
            day.setMeal(meals);
            calendarApp.enterExercise(day,
                    nameInput.getText(), repInput.getText(), setInput.getText(), weightInput.getText());
            window1.close();
        });
        vbox.getChildren().addAll(nameInput,setInput,repInput,weightInput,makeExercise);
        return vbox;
    }

    private static VBox toKeepMethodUnder25() {
        VBox vbox = new VBox();
        Label exerciseName = new Label("Exercise:");

        Label sets = new Label("Sets:");

        Label reps = new Label("Reps:");

        Label weight = new Label("Weight:");
        vbox.getChildren().addAll(exerciseName,sets,reps,weight);
        return vbox;
    }

    public static HBox showJournal(CalendarApp calendarApp) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 15,15,15));
        Stage window = new Stage();
        window.setTitle("View or Edit Days");
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        for (int i = 0; i < calendarApp.calendar.days(); i++) {
            Button viewDate = new Button(calendarApp.calendar.getNth(i).getDate());
            viewDate.setMinWidth(100);
            viewDate.setMinHeight(30);
            int finalI = i;
            viewDate.setOnAction(e -> editDay(calendarApp.calendar.getNth(finalI)));
            layout.getChildren().addAll(viewDate);
        }
        hbox.getChildren().addAll(layout);
        return hbox;
    }

    public static XYChart.Series<String, Number> graphData(CalendarApp calendarApp) {
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setLabel("Day");

        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setLabel("Calories");

        LineChart<String, Number> lineChart = new LineChart<String, Number>(categoryAxis,numberAxis);
        lineChart.setTitle("Calories Over Time");

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Calories");

        for (int i = 0; i < calendarApp.calendar.days(); i++) {
            Day day = calendarApp.calendar.getNth(i);
            data.getData().add(new XYChart.Data<String, Number>(day.getDate(), day.caloriesEaten()));
        }
        return data;
    }

    private static void editDay(Day day) {
        HBox hbox = new HBox();
        hbox.setSpacing(15);
        hbox.setPadding(new Insets(10, 10,10,20));
        window0.setTitle(day.getDate());
        VBox dayLayout = toKeepMethodUnder28(day);
        VBox exerciseLayout = toKeepMethodUnder27(day);
        hbox.getChildren().addAll(dayLayout, exerciseLayout);
        for (int i = 0; i < day.getWorkout().size(); i++) {
            VBox vbox = new VBox();
            vbox.setSpacing(10);
            Label filler = new Label("");
            Label exerciseName1 = new Label(day.getWorkout().get(i).getDescription());

            Label sets1 = new Label(Long.toString(day.getWorkout().get(i).getSets()));

            Label reps1 = new Label(Long.toString(day.getWorkout().get(i).getReps()));
            Label weight1 = new Label(Long.toString(day.getWorkout().get(i).getWeight()));

            vbox.getChildren().addAll(filler,exerciseName1, sets1, reps1, weight1);
            hbox.getChildren().addAll(vbox);
        }
        Scene scene = new Scene(hbox, 300, 200);
        window0.setScene(scene);
        window0.show();

    }

    private static VBox toKeepMethodUnder27(Day day) {
        VBox gridPane = new VBox();
        gridPane.setSpacing(10);
        Label workout = new Label("Workout");

        Label exerciseName = new Label("Exercise:");

        Label sets = new Label("Sets:");

        Label reps = new Label("Reps:");

        Label weight = new Label("Weight:");

        Button addExercise = new Button("Add Exercise");
        addExercise.setOnAction(e -> {
            addExercise(day);
            window0.close();
        });


        gridPane.getChildren().addAll(workout,exerciseName,sets,reps,weight,addExercise);

        return gridPane;
    }

    private static VBox toKeepMethodUnder28(Day day) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Food foodForDay = day.getFood();

        Label macros = new Label("Macro Total");

        Label calories = new Label("Calories: " + foodForDay.getCalories());

        Label protein = new Label("Protein: " + foodForDay.getProtein());

        Label carb = new Label("Carbohydrate: " + foodForDay.getCarbohydrate());

        Label fats = new Label("Fat: " + foodForDay.getFat());

        Button addFood = new Button("Add Food");
        GridPane.setConstraints(addFood, 0,5);
        addFood.setOnAction(e -> {
            newFood(day);
            window0.close();
        });

        vbox.getChildren().addAll(macros,calories,protein,carb,fats,addFood);
        return vbox;
    }

    public static boolean newCalendar(CalendarApp calendarApp) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Reset Calendar");
        window.setMinWidth(250);
        Label label = new Label();
        label.setText("Are you sure you want to reset the calendar? All data will be deleted.");

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(event -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);


        layout.getChildren().addAll(label,yesButton,noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
