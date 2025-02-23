package com.techelevator.dao;

import com.techelevator.model.Score;

import java.util.List;

public interface ScoreDao {

    List<Score> getALlScores();

    List<Score> getScoreByTeeTime(int teeTimeId, int userId);

    List<Score> getScoreByLeagueId(int leagueId, int userId);

    List<Score> getScoreByCourseId(int courseId, int userId);

    List<Score> getUserScore(int userId);

}
