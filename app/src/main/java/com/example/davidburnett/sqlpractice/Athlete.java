package com.example.davidburnett.sqlpractice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import db.SqlRunner;

/**
 * Created by davidburnett on 30/09/2017.
 */

public class Athlete {

    private String name;
    private int id;
    private HashMap<String, ArrayList<Workout>> activityLog;
    private ArrayList<Workout> run;



    public Athlete (String name){
        this.name = name;
    }

    public Athlete (int id, String name){
        this.id = id;
        this.name = name;
        activityLog = new HashMap<>();
        run = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Workout> getDisciplineLog(String discipline) {
        return activityLog.get(discipline);
    }

    public static void deleteAll(){
        String sql = "DELETE FROM athletes;";
        SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

    public void delete(){
        String sql = String.format("DELETE FROM athletes WHERE id = %d;", this.id);
        SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

    public void update(){
        String sql = String.format("UPDATE artists SET name = '%s' WHERE id = %d;", this.name, this.id);
        SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

    public static void all(){
        String sql = "SELECT * FROM athletes;";
        ResultSet rs = SqlRunner.executeQuery(sql);
        try{
            while( rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                System.out.println(id + " " + name);
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            SqlRunner.closeConnection();
        }
    }

    public void getWorkouts(){
        String sql = String.format("SELECT * FROM workouts WHERE athlete_id = %d;", id);
        ResultSet rs = SqlRunner.executeQuery(sql);
        ArrayList run1 = new ArrayList<>();
        try{
            while( rs.next()){
                String discipline = rs.getString("discipline");
                String dateString = rs.getString("dateString");
                int distance = rs.getInt("distance");
                int timeString = rs.getInt("timeString");
                Workout workout = new Workout(discipline, dateString, distance, timeString, this);
                System.out.println(workout);
                run1.add(workout);
                System.out.println(run1);
                System.out.println(this.id);
            }
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            SqlRunner.closeConnection();
        }
    }


    public void addActivityToLog(Workout workout){

        String discipline = workout.getDiscipline();
        ArrayList<Workout> logToAdd = new ArrayList<>();

        if (activityLog.get(discipline) != null){
            logToAdd = activityLog.get(discipline);
        }

        logToAdd.add(workout);
        activityLog.put(discipline, logToAdd);
    }




    public void save(){
        String sql = String.format("INSERT INTO athletes (name) VALUES ('%s');", name);
        this.id = SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

}
