package com.example.stayactive.model;

import java.sql.Time;

public class Exercise {
    private int exerciseId;

    private String name;
    private double kCalPerHour;
    private int reps;
    private Time duration;

    private static final int DEFAULT_ID = 0;

    public Exercise(int exerciseId, String name, double kCalPerHour, int reps, Time duration) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.kCalPerHour = kCalPerHour;
        this.reps = reps;
        this.duration = duration;
    }

    public Exercise(String name, double kCalPerHour, int reps, Time duration) {
        this(DEFAULT_ID, name, kCalPerHour, reps, duration);
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public double getKCalPerHour() {
        return kCalPerHour;
    }

    public int getReps() {
        return reps;
    }

    public Time getDuration() {
        return duration;
    }

}
