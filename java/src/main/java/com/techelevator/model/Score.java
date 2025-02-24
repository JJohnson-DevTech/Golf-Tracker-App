package com.techelevator.model;

public class Score {

    private int scoreId;

    private int userId;

    private int courseId;

    private int leagueId;

    private int totalScore;


    public Score(int scoreId, int userId, int courseId, int leagueId, int totalScore) {
        this.scoreId = scoreId;
        this.userId = userId;
        this.courseId = courseId;
        this.leagueId = leagueId;
        this.totalScore = totalScore;
    }

    public Score(){

    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
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
}
