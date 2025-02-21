package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"totalYards", "par", "holes"})
public class Courses {

    private int courseId;

    private String clubName;

    private String courseName;

    private String address;

    private String city;

    private String state;

    private int totalYards;

    private String country;

    private int par;

    private int holes;

    public Courses(int courseId, String clubName, String courseName, String address, String city, String state, int totalYards, String country, int par, int holes) {
        this.courseId = courseId;
        this.clubName = clubName;
        this.courseName = courseName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.totalYards = totalYards;
        this.par = par;
        this.holes = holes;
    }

    public Courses() {

    }

    public int getTotalYards() { return totalYards; }

    public void setTotalYards(int totalYards) { this.totalYards = totalYards; }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public int getPar() {
        return par;
    }

    public int getHoles() {
        return holes;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public void setHoles(int holes) {
        this.holes = holes;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
