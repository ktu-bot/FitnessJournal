package ui;

import model.*;
import org.json.simple.parser.ParseException;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.List;

public class CalendarApp {
    private static final String CALENDAR_FILE = "./data/calendar.txt";
    public Calendar calendar;

    private void init() {
        calendar = new Calendar();
    }

    public void newCalendar() {
        init();
    }

    public void loadCalendar() {
        try {
            List<Day> calendar1 = Reader.readCalendar();
            calendar = new Calendar();
            for (Day day: calendar1) {
                calendar.newDay(day);
            }
        } catch (IOException | ParseException e) {
            init();
        }
    }

    public void saveCalendar() {
        try {
            Writer writer = new Writer(new File(CALENDAR_FILE));
            writer.write(calendar);
            System.out.println("Accounts saved to file " + CALENDAR_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save accounts to " + CALENDAR_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterExercise(Day day, String name, String set, String rep, String weights) {
        System.out.println("\nPlease enter name of the exercises");
        System.out.println("\nPlease enter number of sets done");
        int sets = validInt(set);
        System.out.println("\nPlease enter number of reps done");
        int reps = validInt(rep);
        System.out.println("\nPlease enter weight used");
        int weight = validInt(weights);

        Exercise exercise = new Exercise(name, sets, reps, weight);
        day.addExercise(exercise);
    }

    private int validInt(String s) {
        int num = 0;

        try {
            num = intParse(s);
        } catch (ValidIntegerException e) {
            System.out.println("We assume you mean 0!");
        }

        return num;
    }

    private int intParse(String s) throws ValidIntegerException {
        int num = 0;

        try {
            num = Integer.parseInt(s);
        } catch (InputMismatchException | NumberFormatException e) {
            throw new ValidIntegerException("It must be an integer!");
        }
        return num;
    }


    public Food enterFood(String cal, String carb, String pro, String fat) {
        System.out.println("\nEnter food details to add to today \n\tcalories:");
        int calInt = validInt(cal);;
        System.out.println("\n\tcarbs:");
        int carbInt = validInt(carb);;
        System.out.println("\n\tproteins:");
        int proInt = validInt(pro);;
        System.out.println("\n\tfats");
        int fatInt = validInt(fat);;

        Food food = new Food(calInt, carbInt, proInt, fatInt);
        return food;
    }

}
