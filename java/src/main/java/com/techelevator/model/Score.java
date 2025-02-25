package com.techelevator.model;

import java.sql.Timestamp;

public class Score {

    private int teeTimeId;

    private int userId;

    private int courseId;

    private int leagueId;

    private int totalScore;

    private Timestamp teeTime;

    public Score(int userId, int courseId, int leagueId, int totalScore, int teeTimeId, Timestamp teeTime) {
        this.userId = userId;
        this.courseId = courseId;
        this.leagueId = leagueId;
        this.totalScore = totalScore;
        this.teeTimeId = teeTimeId;
        this.teeTime = teeTime;
    }

    public Score(){

    }

    public Timestamp getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(Timestamp teeTime) {
        this.teeTime = teeTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
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
}
