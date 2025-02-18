package com.techelevator.model;

import java.sql.Timestamp;

public class Leagues {

    private int leagueId;

    private String leagueName;

    private int leagueHost;

    private Timestamp matchTime;

    public Leagues(int leagueId, String leagueName, int leagueHost, Timestamp matchTime) {
        this.leagueId = leagueId;
        this.leagueName = leagueName;
        this.leagueHost = leagueHost;
        this.matchTime = matchTime;
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

    public Timestamp getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Timestamp matchTime) {
        this.matchTime = matchTime;
    }
}
