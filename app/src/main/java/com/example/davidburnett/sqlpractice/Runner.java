package com.example.davidburnett.sqlpractice;

/**
 * Created by davidburnett on 30/09/2017.
 */

public class Runner {
    public static void main(String[] args) {
//        Athlete.deleteAll();
//
        Athlete athlete1 = new Athlete("Garry");
        Athlete athlete2 = new Athlete("Derek");
        athlete1.save();
//        athlete2.save();
        Workout workout1 = new Workout(2,"Run", "date", 3, 33, athlete1);
        Workout workout2 = new Workout(2,"Run", "date", 4, 44, athlete1);
        Workout workout3 = new Workout(2,"Run", "date", 5, 55, athlete1);
        Workout workout4 = new Workout("Run", "date", 5, 55, athlete2);

//        Workout workout1 = new Workout(2, "Run", "date", 40, 200, athlete1);
//        System.out.println(workout1.getAthlete());
        workout1.update();
        workout2.update();
        workout3.update();

//        workout1.update();
////        Workout.deleteAll();
        Workout.all();
        athlete1.getWorkouts();



    }

}
