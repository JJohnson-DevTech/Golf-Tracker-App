package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcScoreDao implements ScoreDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Score> getALlScores() {
        List<Score> listAllScores = new ArrayList<>();
        String sql = "SELECT * FROM scores;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                listAllScores.add(mapRowToScore(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with getAllScores", e);
        }
        return listAllScores;
    }

    //TODO add tee_time_id to scores table in db
    @Override
    public List<Score> getScoreByTeeTime(int teeTimeId, int userId) {
        if(teeTimeId <= 0) throw new IllegalArgumentException("Tee Time ID must be a positive integer.");
        if(userId <= 0) throw new IllegalArgumentException("User ID must be a positive integer.");
        List<Score> scoreByTeeTime = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE tee_time_id = ? AND user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teeTimeId, userId);
            while(results.next()){
                scoreByTeeTime.add(mapRowToScore(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with getScoreByTeeTime", e);
        }
        return scoreByTeeTime;
    }

    @Override
    public List<Score> getScoreByLeagueId(int leagueId, int userId) {
        if(leagueId <= 0) throw new IllegalArgumentException("League ID must be a positive integer.");
        if(userId <= 0) throw new IllegalArgumentException("User ID must be a positive integer.");
        List<Score> scoreByLeague = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE league_id = ? AND user_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId, userId);
            while(results.next()){
                scoreByLeague.add(mapRowToScore(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with getScoreByLeagueId", e);
        }
        return scoreByLeague;
    }

    @Override
    public List<Score> getScoreByCourseId(int courseId, int userId) {
        if(courseId <= 0) throw new IllegalArgumentException("Course ID must be a positive integer.");
        if(userId <= 0) throw new IllegalArgumentException("User ID must be a positive integer.");
        List<Score> scoreByCourse = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE course_id = ? AND user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId, userId);
            while(results.next()){
                scoreByCourse.add(mapRowToScore(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with getScoreByCourseId", e);
        }
        return scoreByCourse;
    }

    @Override
    public List<Score> getUserScore(int userId) {
        if(userId <= 0) throw new IllegalArgumentException("User ID must be a positive integer.");
        List<Score> userScore = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE user_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()){
                userScore.add(mapRowToScore(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with getUserScore", e);
        }
        return userScore;
    }

    private Score mapRowToScore(SqlRowSet rs){
        Score score = new Score();
        score.setScoreId(rs.getInt("score_id"));
        score.setUserId(rs.getInt("user_id"));
        score.setCourseId(rs.getInt("course_id"));
        score.setLeagueId(rs.getInt("league_id"));
        score.setTotalScore(rs.getInt("total_score"));
        return score;
    }
}
