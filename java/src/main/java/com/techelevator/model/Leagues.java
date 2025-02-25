package com.techelevator.model;

public class Leagues {

    private int leagueId;

    private String leagueName;

    private int leagueHost;

    private int courseId;

    private boolean isActive;

    private int minPlayers;

    public Leagues(int leagueId, String leagueName, int leagueHost, int courseId, boolean isActive, int minPlayers) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueHost = leagueHost;
        this.courseId = courseId;
        this.isActive = isActive;
        this.minPlayers = minPlayers;
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

    public boolean getIsActive() { return isActive; }

    public void setIsActive(boolean isActive) { this.isActive = isActive; }

    public int getMinPlayers() { return minPlayers; }

    public void setMinPlayers(int maxPlayers) { this.minPlayers = maxPlayers; }

}
