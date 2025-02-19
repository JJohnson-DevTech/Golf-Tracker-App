package com.techelevator.model;

public class CoursesDb {

    private int courseId;

    private String courseName;

    private String city;

    private String stateAb;

    private int yardage;

    private int par;

    private int holes;


    public CoursesDb(int courseId, String courseName, String city, String stateAb, int yardage, int par, int holes){
        this.courseId = courseId;
        this.courseName = courseName;
        this.city = city;
        this.stateAb = stateAb;
        this.yardage = yardage;
        this.par = par;
        this.holes = holes;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateAb() {
        return stateAb;
    }

    public void setStateAb(String stateAb) {
        this.stateAb = stateAb;
    }

    public int getYardage() {
        return yardage;
    }

    public void setYardage(int yardage) {
        this.yardage = yardage;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getHoles() {
        return holes;
    }

    public void setHoles(int holes) {
        this.holes = holes;
    }
}
