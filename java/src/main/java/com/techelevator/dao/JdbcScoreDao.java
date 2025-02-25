package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcScoreDao implements ScoreDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Score> getALlScores() {
        List<Score> listAllScores = new ArrayList<>();
        String sql = "SELECT * FROM tee_times;";
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

    @Override
    public List<Score> getScoreByLeagueId(int leagueId, int userId) {
        if(leagueId <= 0) throw new IllegalArgumentException("League ID must be a positive integer.");
        if(userId <= 0) throw new IllegalArgumentException("User ID must be a positive integer.");
        List<Score> scoreByLeague = new ArrayList<>();
        String sql = "SELECT * FROM tee_times WHERE league_id = ? AND user_id = ?;";

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
        String sql = "SELECT * FROM tee_times WHERE course_id = ? AND user_id = ?;";
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
        String sql = "SELECT * FROM tee_times WHERE user_id = ?;";

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


    @Override
    public Score addNewScore(Score score) {
        if(score == null){
            throw new IllegalArgumentException("Score object cannot be null");
        }
        Score newScore = null;
        String sql = "INSERT INTO tee_times (user_id, course_id, league_id, total_score, tee_time) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING tee_time_id;";

        try{
            //newScoreId references tee_time_id 
            int newScoreId = jdbcTemplate.queryForObject(sql, int.class, score.getUserId(), score.getCourseId(), score.getLeagueId(), score.getTotalScore(), score.getTeeTimeId());
             newScore = getScoreByTeeTime(newScoreId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with addNewScore", e);
        }
        return newScore;
    }

    @Override
    public Score getScoreByTeeTime(int teeTimeId) {
        if(teeTimeId <= 0) throw new IllegalArgumentException("Tee Time ID must be a positive integer.");
        Score score = new Score();
        String sql = "SELECT * FROM tee_times WHERE tee_time_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teeTimeId);
            if(results.next()){
                score = mapRowToScore(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e) {
            throw new DaoException("Issue with getScoreByTeeTime", e);
        }
        return score;
    }

    private Score mapRowToScore(SqlRowSet rs){
        Score score = new Score();
        score.setUserId(rs.getInt("user_id"));
        score.setCourseId(rs.getInt("course_id"));
        score.setLeagueId(rs.getInt("league_id"));
        score.setTotalScore(rs.getInt("total_score"));
        score.setTeeTimeId(rs.getInt("tee_time_id"));
        score.setTeeTime(rs.getTimestamp("tee_time"));
        return score;
    }
}
