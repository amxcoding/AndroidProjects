package com.example.stayactive.model;

import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.util.List;

public class Alert {
    private int alertId;

    private String name;
    private LocalTime start; // start and end time when the alerts should go off
    private LocalTime end;
    private LocalTime alertInterval; // the interval of the alert: e.g. every 60 mins an alert to do x amount of exercies
    private boolean[] repeatOnDays; // days to repeat the alert on size 7 -> 0 to 6
    private List<Exercise> exercisesOnAlert; // exercises that needs to be done during the alert
    private boolean randomExercises; // if the exercises should be generated on random
    private int maxNumberOfExercises; // maximum number of exercise per alert
    private boolean isActive; // if an alert is active/ in use

    private static final int DEFAULT_ID = 0; // when I create new alert default ID
    /*
    Default constructor
     */
    public Alert(int alertId, String name, LocalTime start, LocalTime end, LocalTime alertInterval, boolean[] repeatOnDays, List<Exercise> exercisesOnAlert, boolean randomExercises, int maxNumberOfExercises, boolean isActive) {
        this.alertId = alertId;
        this.name = name;
        this.start = start;
        this.end = end;
        this.alertInterval = alertInterval;
        this.repeatOnDays = repeatOnDays;
        this.exercisesOnAlert = exercisesOnAlert;
        this.randomExercises = randomExercises;
        this.maxNumberOfExercises = maxNumberOfExercises;
        this.isActive = isActive;
    }

    /*
    Constructor without id.
    Used when I create a new alert
    The default id will be automatically incremented.
     */
    public Alert(String name, LocalTime start, LocalTime end, LocalTime alertInterval, boolean[] repeatOnDays, List<Exercise> exercisesOnAlert, boolean randomExercises, int maxNumberOfExercises, boolean isActive) {
        this(DEFAULT_ID, name, start, end, alertInterval, repeatOnDays, exercisesOnAlert, randomExercises, maxNumberOfExercises, isActive);
    }

    public int getAlertId() {
        return alertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStart() {
        return start;
    }

    /*
    Whenever you want to change the start time
     */
    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    /*
    Whenever you want to change the end time
     */
    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalTime getAlertInterval() {
        return alertInterval;
    }

    /*
    Whenever you want to change alert interval
     */
    public void setAlertInterval(LocalTime alertInterval) {
        this.alertInterval = alertInterval;
    }

    public boolean[] getRepeatOnDays() {
        return repeatOnDays;
    }

    /*
    Whenever you want to change when it should repeat
     */
    public void setRepeatOnDays(boolean[] repeatOnDays) {
        this.repeatOnDays = repeatOnDays;
    }

    public List<Exercise> getExercisesOnAlert() {
        return exercisesOnAlert;
    }

    /*
    Whenever you want to change the exercises you want to do on an alert
     */
    public void setExercisesOnAlert(List<Exercise> exercisesOnAlert) {
        this.exercisesOnAlert = exercisesOnAlert;
    }

    public boolean isRandomExercises() {
        return randomExercises;
    }

    /*
    if the exercises should be randomly generated
     */
    public void setRandomExercises(boolean randomExercises) {
        this.randomExercises = randomExercises;
    }

    public int getMaxNumberOfExercises() {
        return maxNumberOfExercises;
    }

    /*
    If you want to change the max number of exercises per alert
     */
    public void setMaxNumberOfExercises(int maxNumberOfExercises) {
        this.maxNumberOfExercises = maxNumberOfExercises;
    }

    public boolean isActive() {
        return isActive;
    }

    /*
    If you want to turn on/off the alert
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /*
    Needs to be overridden to be able to compare two alerts.
    This is used when filling the recyclerview list.
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            final Alert alert = (Alert) obj;

            if (this.getAlertId() != alert.getAlertId()) {
                return false;
                // same id so some item, check if something has changed
            } else {
                if (!this.getName().equals(alert.getName()) ||
                this.getStart() != alert.getStart() ||
                this.getEnd() != alert.getEnd() ||
                this.getAlertInterval() != getAlertInterval() ||
                this.getRepeatOnDays() != alert.getRepeatOnDays() ||
                this.getExercisesOnAlert() != alert.getExercisesOnAlert() ||
                this.isRandomExercises() != alert.isRandomExercises() ||
                this.getMaxNumberOfExercises() != alert.getMaxNumberOfExercises() ||
                this.isActive() != alert.isActive()) {
                    return false;
                }
            }
        }
        // item is the same
        return true;
    }
}
