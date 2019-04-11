package com.example.swimtracker;

import java.util.ArrayList;

public class Set {

    private ArrayList<Integer> reps;
    private ArrayList<Integer> distances;
    private int rounds;
    private int yardage;

    public Set() {
        reps = new ArrayList<>();
        distances = new ArrayList<>();
        rounds = 1;
        yardage = 0;
    }

    public void addPart(int rep, int distance) {
        reps.add(rep);
        distances.add(distance);
        yardage += rep * distance;
    }

    public void addRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getYardage() {
        return yardage;
    }

    public String toString() {

        String set = "";

        // if there is more than one round, add brackets with number of times
        if (rounds > 1) {
            set = set + "[" + rounds + "x]\n";
        }

        // add in each part of the set
        for (int i = 0; i<reps.size(); i++) {
            set = set + reps.get(i) + " x " + distances.get(i) + "\n";
        }

        // skip a line before next set
        set = set + "\n";

        return set;
    }
}
