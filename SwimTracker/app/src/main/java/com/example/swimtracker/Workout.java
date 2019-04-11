package com.example.swimtracker;

import java.util.ArrayList;

public class Workout {

    private ArrayList<Set> sets;

    public Workout() {
        sets = new ArrayList<>();
    }

    public void addSet(Set set) {
        sets.add(set);
    }

    public int getTotalYardage() {
        int yardage = 0;

        for (Set s : sets) {
            yardage += s.getYardage();
        }

        return yardage;
    }

    public String toString() {

        String workout = "";

        for (Set s : sets) {
            workout = workout + s.toString();
        }

        return workout;
    }

}
