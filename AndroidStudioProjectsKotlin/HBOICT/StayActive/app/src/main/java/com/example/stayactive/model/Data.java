package com.example.stayactive.model;

import java.sql.Time;
import java.util.HashMap;

public class Data {
    private int dataId;

    private Time exerciseDate;
    private HashMap<Exercise, Double> exercisesDone; // calories burned per exercises
    private double totalKCaloriesBurned;
    private Time totalAmountOfActivity;

    private static final int DEFAULT_ID = 0;

    public Data(int dataId, Time exerciseDate, HashMap<Exercise, Double> exercisesDone, double totalKCaloriesBurned, Time totalAmountOfActivity) {
        this.dataId = dataId;
        this.exerciseDate = exerciseDate;
        this.exercisesDone = exercisesDone;
        this.totalKCaloriesBurned = totalKCaloriesBurned;
        this.totalAmountOfActivity = totalAmountOfActivity;
    }

    public Data(Time exerciseDate, HashMap<Exercise, Double> exercisesDone, double totalKCaloriesBurned, Time totalAmountOfActivity) {
        this(DEFAULT_ID, exerciseDate, exercisesDone, totalKCaloriesBurned, totalAmountOfActivity);
    }

    public int getDataId() {
        return dataId;
    }

    public Time getExerciseDate() {
        return exerciseDate;
    }

    public HashMap<Exercise, Double> getExercisesDone() {
        return exercisesDone;
    }

    /*
    List changes every time an exercise is done
     */
    public void setExercisesDone(HashMap<Exercise, Double> exercisesDone) {
        this.exercisesDone = exercisesDone;
    }

    public double getTotalKCaloriesBurned() {
        return totalKCaloriesBurned;
    }

    public void setTotalKCaloriesBurned(double totalKCaloriesBurned) {
        this.totalKCaloriesBurned = totalKCaloriesBurned;
    }

    public Time getTotalAmountOfActivity() {
        return totalAmountOfActivity;
    }

    public void setTotalAmountOfActivity(Time totalAmountOfActivity) {
        this.totalAmountOfActivity = totalAmountOfActivity;
    }
}
