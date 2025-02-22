package com.techelevator.dao;

import com.techelevator.model.Score;

import java.util.List;

public interface ScoreDao {

    List<Score> getALlScores();

    List<Score> getScoreByTeeTime(int teeTimeId);

    List<Score> getScoreByLeagueId(int leagueId);

    //to get a course leaderboard possibly?
    List<Score> getScoreByCourseId(int courseId);

    Score getUserScore(int userId);

    //was thinking to implement a getAverageUserScore, getHighestUserScore, getLowestUserScore
    //?
}
