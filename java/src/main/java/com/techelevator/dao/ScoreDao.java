package com.techelevator.dao;

import com.techelevator.model.Score;
import com.techelevator.model.TeeTime;

import java.util.List;

public interface ScoreDao {

    List<Score> getALlScores();

    List<Score> getScoreByLeagueId(int leagueId, int userId);

    List<Score> getScoreByCourseId(int courseId, int userId);

    List<Score> getUserScore(int userId);

    Score addNewScore(Score score);

    Score getScoreByTeeTime(int teeTimeId);

    Score getTotalOfPast5Scores(int user_id);
}
