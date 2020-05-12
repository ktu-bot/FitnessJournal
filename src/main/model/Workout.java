package model;


import java.util.ArrayList;
import java.util.List;

public class Workout {
    List<Exercise> workout = new ArrayList<>();

    public void addExerciseToWorkout(Exercise e) {
        workout.add(e);
    }
}
