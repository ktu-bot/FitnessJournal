package model;

// represents the food eaten, with macronutrients and name of food

public class Food {

    long calories;       // amount of calories in food
    long carbohydrate;   // amount of carbohydrates in food
    long protein;        // amount of protein in food
    long fat;            // amount of fat in food

    public Food(long c, long carbs, long p, long f) {
        calories = c;
        carbohydrate = carbs;
        protein = p;
        fat = f;
    }

    public long getCalories() {
        return calories;
    }

    public long getCarbohydrate() {
        return carbohydrate;
    }

    public long getFat() {
        return fat;
    }

    public long getProtein() {
        return protein;
    }
}
