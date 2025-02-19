package com.techelevator.model;

public class Courses {

    private int courseId;

    private String clubName;

    private String courseName;

    private String address;

    private String city;

    private String state;

    private String country;

    public Courses(int courseId, String clubName, String courseName, String address, String city, String state, String country) {
        this.courseId = courseId;
        this.clubName = clubName;
        this.courseName = courseName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getClubName() {
        return clubName;
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
}
