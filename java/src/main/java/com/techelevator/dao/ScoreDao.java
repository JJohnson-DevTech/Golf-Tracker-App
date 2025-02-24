package com.techelevator.dao;

import com.techelevator.model.Score;

import java.util.List;

public interface ScoreDao {

    List<Score> getALlScores();

    List<Score> getScoreByLeagueId(int leagueId, int userId);

    List<Score> getScoreByCourseId(int courseId, int userId);

    List<Score> getUserScore(int userId);

    Score getScoreByScoreId(int scoreId);

    Score addNewScore(int courseId, int leagueId, int userId, int totalScore);

}
