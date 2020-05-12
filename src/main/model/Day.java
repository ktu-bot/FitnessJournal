package model;

import java.util.ArrayList;
import java.util.List;


public class Day {

    String date;
    Workout workout;
    List<Food> meal;

    public Day() {
        super();
    }

    public Day(String date, Workout workout, List<Food> meal) {
        this.date = date;
        this.workout = workout;
        this.meal = meal;
    }

    public String getDate() {
        return date;
    }

    public Food getFood() {
        Food s = new Food(caloriesEaten(),carbsConsumed(),proteinConsumed(),fatsConsumed());
        return s;
    }

    public List<Exercise> getWorkout() {
        List<Exercise> s = new ArrayList<>();
        for (Exercise e : workout.workout) {
            Exercise newExercise = new Exercise(e.description,e.set,e.rep,e.weight);
            s.add(newExercise);
        }
        return s;
    }

    public void addExercise(Exercise e) {
        workout.addExerciseToWorkout(e);
    }

    public void newFood(Food food) {
        meal.add(food);
    }


    public long caloriesEaten() {
        long calories = 0;
        for (Food food : meal) {
            calories = calories + food.calories;
        }
        return calories;
    }

    public long proteinConsumed() {
        long protein = 0;
        for (Food food : meal) {
            protein = protein + food.protein;
        }
        return protein;
    }

    public long carbsConsumed() {
        long carbs = 0;
        for (Food food : meal) {
            carbs = carbs + food.carbohydrate;
        }
        return carbs;
    }

    public long fatsConsumed() {
        long fat = 0;
        for (Food food : meal) {
            fat = fat + food.fat;
        }
        return fat;
    }

    public void setMeal(List<Food> meal) {
        this.meal = meal;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
