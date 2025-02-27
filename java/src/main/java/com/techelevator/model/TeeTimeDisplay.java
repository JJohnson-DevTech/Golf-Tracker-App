package com.techelevator.model;

import java.sql.Timestamp;

public class TeeTimeDisplay {
    private int tee_time_id;
    private String course_name;
    private String club_name;
    private String league_name;
    private Timestamp tee_time;
    private int num_players;

    public int getTee_time_id() {
        return tee_time_id;
    }

    public int getNum_players() {
        return num_players;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    public void setTee_time_id(int tee_time_id) {
        this.tee_time_id = tee_time_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public Timestamp getTee_time() {
        return tee_time;
    }

    public void setTee_time(Timestamp tee_time) {
        this.tee_time = tee_time;
    }
}
