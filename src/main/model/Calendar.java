package model;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    List<Day> calendar = new ArrayList<>();

    public Calendar() {

    }

    public int days() {
        return calendar.size();
    }

    public void newDay(Day day) {
        calendar.add(day);
    }

    public Day getNth(int i) {
        Day day = calendar.get(i);
        return day;
    }

    public Day findDate(String date) {
        int counter = 0;
        int pos = 0;
        for (Day day : calendar) {
            if (date.equals(day.date)) {
                pos = counter;
            } else {
                counter = counter + 1;
            }
        }
        return calendar.get(pos);
    }
}
