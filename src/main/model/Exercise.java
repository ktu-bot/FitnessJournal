package model;

// represents different kinds of exercises, a exercise has a description, sets, reps and weight used.
public class Exercise {
    String description;   // name/description of lift
    long set;              // amount of sets done
    long rep;              // amount of reps done
    long weight;           // amount of weight used

    // REQUIRES: s, r is positive integer, name of non-zero length
    // EFFECTS: description is set to name
    //          set set to s
    //          rep set to r
    //          weight set to w
    public Exercise(String name, long s, long r, long w) {
        description = name;
        set = s;
        rep = r;
        weight = w;
    }

    public long getWeight() {
        return weight;
    }

    public long getSets() {
        return set;
    }

    public long getReps() {
        return rep;
    }

    public String getDescription() {
        return description;
    }
}
