package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.TeeTime;
import com.techelevator.model.TeeTimeDisplay;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTeeTimeDao implements TeeTimeDao{

    private final JdbcTemplate jdbcTemplate;
    private final JdbcCourseDao jdbcCourseDao;
    private final JdbcLeaguesDao jdbcLeaguesDao;

    public JdbcTeeTimeDao(JdbcTemplate jdbcTemplate, JdbcCourseDao jdbcCourseDao, JdbcLeaguesDao jdbcLeaguesDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcCourseDao = jdbcCourseDao;
        this.jdbcLeaguesDao = jdbcLeaguesDao;
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

    @Override
    public List<TeeTimeDisplay> getTeeTimeByUserId(int userId) {
        List<TeeTimeDisplay> teeTimes = new ArrayList<>();
        String sql = "SELECT * FROM tee_times AS tt JOIN golf_courses AS gc on tt.course_id = gc.course_id LEFT JOIN leagues AS l ON tt.league_id = l.league_id WHERE user_id = ?;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while(results.next()){
                TeeTimeDisplay teeTime = mapRowToTeeTimeDisplay(results);
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
        TeeTime newTeeTime = null;
        String sql = "INSERT INTO tee_times (course_id, user_id, league_id, tee_time, num_players) VALUES (?,?,?,?,?) RETURNING tee_time_id;";
        int newTeeTimeId = jdbcTemplate.queryForObject(sql, int.class, teeTime.getCourseId(), teeTime.getUserId(), teeTime.getLeagueId(), teeTime.getTeeTime(), teeTime.getNumPlayers() );
        newTeeTime = getTeeTimeById(newTeeTimeId);

        return newTeeTime;
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
                "tee_time = ?, " +
                "total_score = ? " +
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
        teeTime.setNumPlayers(rs.getInt("num_players"));
        return teeTime;
    }

    private TeeTimeDisplay mapRowToTeeTimeDisplay(SqlRowSet rs){
        TeeTimeDisplay teeTime = new TeeTimeDisplay();
        teeTime.setTee_time_id(rs.getInt("tee_time_id"));
        teeTime.setCourse_name(rs.getString("course_name"));
        teeTime.setClub_name(rs.getString("club_name"));
        teeTime.setLeague_name(rs.getString("league_name"));
        teeTime.setTee_time(rs.getTimestamp("tee_time"));
        teeTime.setNum_players(rs.getInt("num_players"));
        return teeTime;
    }
}
