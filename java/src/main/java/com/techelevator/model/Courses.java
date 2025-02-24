package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"totalYards", "par", "holes"})
public class Courses {

    private int courseId;

    private String clubName;

    private String courseName;

    private String address;

    private String city;

    private String state;

    private String country;

    private int par;

    public Courses(int courseId, String clubName, String courseName, String address, String city, String state, String country, int par) {
        this.courseId = courseId;
        this.clubName = clubName;
        this.courseName = courseName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.par = par;
    }

    public Courses() {}

    @JsonProperty("id")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getClubName() {
        return clubName;
    }

    @JsonProperty("club_name")
    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    @JsonProperty("course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @JsonProperty("location.address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("location.city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("location.state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("location.country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

}
