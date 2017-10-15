package com.example.davidburnett.sqlpractice;

import java.sql.ResultSet;
import java.util.Calendar;

import db.SqlRunner;

/**
 * Created by davidburnett on 30/09/2017.
 */

public class Workout {

    private String discipline;
    private String dateString;
    private int time;
    private int distance;
    private Athlete athlete;
    private int id;

    public Workout(String discipline, String dateString, int distance, int time, Athlete athlete) {
        this.discipline = discipline;
        this.dateString = dateString;
        this.distance = distance;
        this.time = time;
        this.athlete = athlete;
    }
    public Workout(int id, String discipline, String dateString, int distance, int time, Athlete athlete) {
        this.discipline = discipline;
        this.dateString = dateString;
        this.distance = distance;
        this.time = time;
        this.athlete = athlete;
        this.id = id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public void save() {
        int athlete_id = athlete.getId();
        String sql = String.format(
                "INSERT INTO workouts (dateString, discipline, distance, timeString, athlete_id) VALUES ('%s', '%s', %d, %d, %d);"
                , this.dateString, this.discipline, this.distance, this.time, athlete_id);

        this.id = SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }


    public static void all(){
        String sql = "SELECT * FROM workouts;";
        ResultSet rs = SqlRunner.executeQuery(sql);
        try {
            while (rs.next()) {
                String discipline = rs.getString("discipline");
                String dateString = rs.getString("dateString");
                int distance = rs.getInt("distance");
                int timeString = rs.getInt("timeString");
                int athleteId = rs.getInt("athlete_id");
                System.out.println(discipline);
                System.out.println(dateString);
                System.out.println(distance);
                System.out.println(timeString);
                System.out.println(athleteId);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            System.exit(0);
        } finally {
            SqlRunner.closeConnection();
        }

    }

    public static void deleteAll(){
        String sql = "DELETE FROM workouts;";
        SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

    public void update() {
        String sql = String.format(
                "UPDATE workouts SET discipline = '%s', dateString = '%s', distance = %d, timeString = %d, athlete_id = %d " +
                        "WHERE id = %d;" ,
                this.discipline, this.dateString, this.distance, this.time, this.athlete.getId(), this.id
        );
        SqlRunner.executeUpdate(sql);
        SqlRunner.closeConnection();
    }

    public String getDiscipline() {
        return discipline;
    }

//    public static void getWorkouts(Athlete athlete){
//        String sql = String.format("SELECT * FROM workouts WHERE athlete_id = '%s';", athlete.getId());
//        ResultSet rs = SqlRunner.executeQuery(sql);
//        try{
//            while( rs.next()){
//                String discipline = rs.getString("discipline");
//                String dateString = rs.getString("dateString");
//                int distance = rs.getInt("distance");
//                int timeString = rs.getInt("timeString");
//                Workout workout = new Workout(discipline, dateString, distance, timeString, athlete);
//                athlete.addActivityToLog(workout);
//                System.out.println(workout);
//                System.out.println(athlete.getDisciplineLog("Run"));
//
//
//            }
//        } catch ( Exception e ) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        } finally {
//            SqlRunner.closeConnection();
//        }
//    }



//    public static Employee getEmployeeByName(String searchName) {
//        Employee employee = null;
//        String sql = String.format
//                ("SELECT employees.id, employees.name, employees.salary, employees.department_id, departments.title as department_title FROM employees INNER JOIN departments ON employees.department_id = departments.id WHERE employees.name = '%s';", searchName);
//        ResultSet rs = SqlRunner.executeQuery(sql);
//        try {
//            if (rs.next()) {
//                int department_id = rs.getInt("department_id");
//                String department_title = rs.getString("department_title");
//
//                String name = rs.getString("name");
//                double salary = rs.getDouble("salary");
//                int employee_id = rs.getInt("id");
//
//                Department dep = new Department(department_id, department_title);
//                employee = new Employee(employee_id, name, dep, salary);
//                return employee;
//            }
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + " : " + e.getMessage());
//            System.exit(0);
//        } finally {
//            SqlRunner.closeConnection();
//            return employee;
//
//        }
//    }

//    public static  Workout getWorkout










}
