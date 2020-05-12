package persistence;

import model.Calendar;
import model.Day;
import model.Exercise;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

// A writer that can write account data to a file
public class Writer {
    private FileWriter fileWriter;
    private JSONArray calendar1 = new JSONArray();

    public Writer(File file) throws IOException {
        fileWriter = new FileWriter(file, true);
    }

    public void write(Calendar calendar) throws FileNotFoundException, UnsupportedEncodingException {
        int size = calendar.days();
        for (int i = 0; i < size; i++) {
            JSONObject dayDetails = new JSONObject();
            Day day = calendar.getNth(i);
            dayDetails.put("date", day.getDate());
            JSONArray workoutDetails = (JSONArray) writeExercise(day);
            dayDetails.put("workout", workoutDetails);

            JSONObject foodDetails = new JSONObject();
            foodDetails.put("calories", day.caloriesEaten());
            foodDetails.put("protein", day.proteinConsumed());
            foodDetails.put("carb", day.carbsConsumed());
            foodDetails.put("fat", day.fatsConsumed());
            dayDetails.put("meal", foodDetails);

            JSONObject day1 = new JSONObject();
            day1.put("day",dayDetails);
            calendar1.add(day1);
        }

        try (FileWriter file = new FileWriter("./data/calendar.json")) {

            file.write(calendar1.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray writeExercise(Day day) {
        JSONArray jsonWorkout = new JSONArray();
        for (Exercise exercise: day.getWorkout()) {
            JSONObject jsonExerciseDetails = new JSONObject();
            JSONObject jsonExercise = new JSONObject();
            String description = exercise.getDescription();
            long rep = exercise.getReps();
            long set = exercise.getSets();
            long weight = exercise.getWeight();
            jsonExerciseDetails.put("description", description);
            jsonExerciseDetails.put("set", set);
            jsonExerciseDetails.put("rep", rep);
            jsonExerciseDetails.put("weight", weight);
            jsonExercise.put("exercise", jsonExerciseDetails);
            jsonWorkout.add(jsonExercise);
        }
        return jsonWorkout;
    }
}