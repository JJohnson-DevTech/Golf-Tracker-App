package com.techelevator.model;

import java.sql.Timestamp;

public class TeeTime {

    private int teeTimeId;

    private int courseId;

    private int userId;

    private Integer leagueId;

    private Timestamp teeTime;

    private int numPlayers;

    public TeeTime(int teeTimeId, int courseId, int userId, Integer leagueId, Timestamp teeTime, int numPlayers) {
        this.teeTimeId = teeTimeId;
        this.courseId = courseId;
        this.userId = userId;
        this.leagueId = leagueId;
        this.teeTime = teeTime;
        this.numPlayers = numPlayers;
    }

    public TeeTime(){

    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
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

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Timestamp getTeeTime() {
        return teeTime;
    }

    public void setTeeTime(Timestamp teeTime) {
        this.teeTime = teeTime;
    }

}
