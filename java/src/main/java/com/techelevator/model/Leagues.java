package com.techelevator.model;

import java.sql.Timestamp;

public class Leagues {

    private int leagueId;

    private String leagueName;

    private int leagueHost;

    private int courseId;

    private Timestamp matchTime;

    private boolean isActive;

    private int maxPlayers;

    public Leagues(int leagueId, String leagueName, int leagueHost, int courseId, Timestamp matchTime, boolean isActive, int maxPlayers) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueHost = leagueHost;
        this.courseId = courseId;
        this.matchTime = matchTime;
        this.isActive = isActive;
        this.maxPlayers = maxPlayers;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getLeagueHost() {
        return leagueHost;
    }

    public void setLeagueHost(int leagueHost) {
        this.leagueHost = leagueHost;
    }

    public int getCourseId() { return courseId; }

    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Timestamp getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Timestamp matchTime) {
        this.matchTime = matchTime;
    }

    public boolean getIsActive() { return isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public int getMaxPlayers() { return maxPlayers; }

    public void setMaxPlayers(int maxPlayers) { this.maxPlayers = maxPlayers; }

}
