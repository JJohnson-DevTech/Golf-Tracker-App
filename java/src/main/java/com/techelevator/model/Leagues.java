package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class Leagues {

    private int leagueId;

    private String leagueName;

    private int leagueHost;

    private int courseId;

    private boolean isActive;

    private int minPlayers;

    private List<User> leagueUsers = new ArrayList<>();

    public Leagues(){}
    private String courseName;

    private String clubName;

    public Leagues(int leagueId, String leagueName, int leagueHost, int courseId, boolean isActive, int minPlayers, List<User> leagueUsers) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueHost = leagueHost;
        this.courseId = courseId;
        this.isActive = isActive;
        this.minPlayers = minPlayers;
        this.leagueUsers = new ArrayList<>();

    }


    public Leagues(int leagueId, String leagueName, int leagueHost, int courseId, boolean isActive, int minPlayers) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueHost = leagueHost;
        this.courseId = courseId;
        this.isActive = isActive;
        this.minPlayers = minPlayers;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<User> getLeagueUsers() {
        return leagueUsers;
    }

    public void setLeagueUsers(List<User> leagueUsers) {
        this.leagueUsers = leagueUsers;
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
