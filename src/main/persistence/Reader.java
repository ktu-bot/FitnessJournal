package persistence;


import model.Day;
import model.Exercise;
import model.Food;
import model.Workout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// A reader that can read calendar data from a file
public class Reader {
    // EFFECTS: returns a list of days parsed from file; throws
    // IOException if an exception is raised when opening / reading from file

    public static List<Day> readCalendar() throws IOException, ParseException {
        List<Day> dayList = null;
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("./data/calendar.json");
        Object obj = parser.parse(reader);
        JSONArray jsonArray = (JSONArray) obj;
        dayList = parseDays(jsonArray);
        return dayList;
    }

    private static List<Day> parseDays(JSONArray jsonArray) {
        List<Day> dayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject dayObject = (JSONObject) jsonArray.get(i);
            JSONObject thisDay = (JSONObject) dayObject.get("day");
            String date = (String) thisDay.get("date");

            JSONObject thisFood = (JSONObject) thisDay.get("meal");
            List<Food> foods = new ArrayList<>();
            foods.add(parseFood(thisFood));

            JSONArray jsonWorkout = (JSONArray) thisDay.get("workout");
            Workout workout = parseWorkout(jsonWorkout);

            Day day = new Day(date, workout, foods);
            dayList.add(day);
        }
        return dayList;
    }

    //create food obj and add to foods
    public static Food parseFood(JSONObject thisFood) {
        Long calories = (Long) thisFood.get("calories");
        Long protein = (Long) thisFood.get("protein");
        Long carb = (Long) thisFood.get("carb");
        Long fat = (Long) thisFood.get("fat");
        Food food = new Food(calories,carb,protein,fat);
        return food;
    }

    //create json obj to parse workout obj
    public static Workout parseWorkout(JSONArray jsonWorkout) {
        Workout workout = new Workout();
        for (int j = 0; j < jsonWorkout.size(); j++) {
            JSONObject workoutObject = (JSONObject) jsonWorkout.get(j);
            JSONObject thisExercise = (JSONObject) workoutObject.get("exercise");
            String description = (String) thisExercise.get("description");
            Long sets = (Long) thisExercise.get("set");
            Long reps = (Long) thisExercise.get("rep");
            Long weight = (Long) thisExercise.get("weight");
            Exercise exercise = new Exercise(description, sets, reps, weight);
            workout.addExerciseToWorkout(exercise);
        }
        return workout;
    }
}