package com.techelevator.model;

import java.sql.Timestamp;

public class TeeTime {

    private int teeTimeId;

    private int courseId;

    private int userId;

    private int leagueId;

    private Timestamp teeTime;

    private int totalScore;

    public TeeTime(int teeTimeId, int courseId, int userId, int leagueId, Timestamp teeTime, int totalScore) {
        this.teeTimeId = teeTimeId;
        this.courseId = courseId;
        this.userId = userId;
        this.leagueId = leagueId;
        this.teeTime = teeTime;
        this.totalScore = totalScore;
    }

    public TeeTime(){

    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTeeTimeId() {
        return teeTimeId;
    }

    public void setTeeTimeId(int teeTimeId) {
        this.teeTimeId = teeTimeId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public Timestamp getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(Timestamp teeTime) {
        this.teeTime = teeTime;
    }

}
