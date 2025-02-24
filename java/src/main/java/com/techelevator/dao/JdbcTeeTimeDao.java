package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.TeeTime;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcTeeTimeDao implements TeeTimeDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeeTimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TeeTime> getAllTeeTimes() {
        List<TeeTime> teeTimes = new ArrayList<>();
        String sql = "SELECT * FROM tee_times;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                TeeTime teeTime = mapRowToTeeTime(results);
                teeTimes.add(teeTime);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getAllTeeTimes", e);
        }
        return teeTimes;
    }

    //TODO add boolean to db to know if its passed or not
    @Override
    public List<TeeTime> getUpcomingTeeTimes() {
        return List.of();
    }

    @Override
    public TeeTime createTeeTime(TeeTime teeTime) {
    if(teeTime == null){
        throw new IllegalArgumentException("teeTime object cannot be null.");
    }
    String sql = "INSERT INTO tee_times (tee_time_id, course_id, user_id, league_id, tee_time) " +
            "VALUES (LOWER(TRIM(?)), ?, ?, ?, ?) RETURNING tee_time_id;";
    try {
        int newTeeTimeId = jdbcTemplate.queryForObject(sql, int.class, teeTime.getTeeTimeId(), teeTime.getCourseId(),
                teeTime.getUserId(), teeTime.getLeagueId(), teeTime.getTeeTime());
        teeTime = getTeeTimeById(newTeeTimeId);
    } catch (CannotGetJdbcConnectionException e) {
        throw new DaoException("Unable to connect to server or database", e);
    } catch (Exception e) {
        throw new DaoException("Issue with createCourse", e);
    }
    return teeTime;
    }

    //TODO create active status in db or work on tomorrow messing with timestamp
    @Override
    public boolean activeMatch(TeeTime teeTime) {
        return false;
    }

    @Override
    public TeeTime getTeeTimeById(int teeTimeId) {
        if(teeTimeId <= 0) throw new IllegalArgumentException("TeeTime ID must be a positive integer");
        TeeTime teeTime = null;
        String sql = "SELECT * FROM tee_times WHERE tee_time_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teeTimeId);
            if (results.next()){
                teeTime = mapRowToTeeTime(results);
            } else {
                throw new DaoException("No TeeTime found with ID: " + teeTimeId);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getTeeTimeById", e);
        }
        return teeTime;
    }

    @Override
    public TeeTime getTeeTimeByLeagueId(int leagueId) {
        if(leagueId <= 0 ) throw new IllegalArgumentException("League ID must be a positive integer.");

        TeeTime teeTime = null;
        String sql = "SELECT * FROM tee_times WHERE league_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, leagueId);
            if(results.next()){
                teeTime = mapRowToTeeTime(results);
            } else {
                throw new DaoException("No TeeTime found with the League ID: " + leagueId);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getTeeTimeByLeagueId", e);
        }
        return teeTime;
    }

    @Override
    public TeeTime getTeeTimeByCourseId(int courseId) {
        if(courseId <= 0) throw new IllegalArgumentException("Course ID must be a positive integer.");

        TeeTime teeTime = null;
        String sql = "SELECT * FROM tee_times WHERE course_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
            if(results.next()){
                teeTime = mapRowToTeeTime(results);
            } else {
                throw new DaoException("No TeeTime found with that Course ID: " + courseId);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with getTeeTimeByCourseId", e);
        }
         return teeTime;
    }

    @Override
    public void updateTeeTime(TeeTime teeTime) {
        if(teeTime == null) throw new IllegalArgumentException("teeTime object is null.");

        TeeTime updatedTeeTime = null;
        String sql = "UPDATE tee_times SET " +
                "course_id = ?," +
                " user_id = ?" +
                ", league_id = ?, " +
                "tee_time = ? " +
                "WHERE tee_time_id = ?;";

        try{
            jdbcTemplate.update(sql, teeTime.getCourseId(), teeTime.getUserId(), teeTime.getLeagueId(),
                    teeTime.getTeeTime(), teeTime.getTeeTimeId());
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with updateTeeTime", e);
        }

    }

    @Override
    public void deleteTeeTime(int teeTimeId) {
        if(teeTimeId <= 0) throw new IllegalArgumentException("teeTimeId must be a positive integer.");
        String sql = "DELETE FROM tee_times WHERE tee_time_id = ?";
        try{
            jdbcTemplate.update(sql, teeTimeId);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        } catch (Exception e){
            throw new DaoException("Issue with deleteTeeTime", e);
        }

    }

    private TeeTime mapRowToTeeTime(SqlRowSet rs){
        TeeTime teeTime = new TeeTime();
        teeTime.setTeeTimeId(rs.getInt("tee_time_id"));
        teeTime.setCourseId(rs.getInt("course_id"));
        teeTime.setUserId(rs.getInt("user_id"));
        teeTime.setLeagueId(rs.getInt("league_id"));
        teeTime.setTeeTime(rs.getTimestamp("tee_time"));
        return teeTime;
    }
}
