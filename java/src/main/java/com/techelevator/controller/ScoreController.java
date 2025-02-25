package com.techelevator.controller;

import com.techelevator.dao.JdbcScoreDao;
import com.techelevator.dao.ScoreDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import org.apache.coyote.Response;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/scores/")
public class ScoreController{

    private final JdbcScoreDao jdbcScoreDao;

    public ScoreController(JdbcScoreDao jdbcScoreDao) {
        this.jdbcScoreDao = jdbcScoreDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Score> getAllScores(){
        return jdbcScoreDao.getALlScores();
    }

    @GetMapping("{teeTimeId}")
    public ResponseEntity<Score> getScoreById(@PathVariable int teeTimeId){
        if(teeTimeId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Score score = jdbcScoreDao.getScoreByTeeTime(teeTimeId);
            if(score == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(score);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'getScoreById'");
        }

    }

    @GetMapping("league")
    public ResponseEntity<Score> getScoreByLeagueId(@RequestParam int userId, @RequestParam int leagueId){
        if( userId <= 0 || leagueId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        try{
            List<Score> scores = jdbcScoreDao.getScoreByLeagueId(userId, leagueId);
            if(scores.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(scores.get(0));
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'getScoreByLeagueId'");
        }
    }

    @GetMapping("course")
    public ResponseEntity<Score> getScoreByCourseId(@RequestParam int userId, @RequestParam int courseId){
        if(userId <= 0 || courseId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            List<Score> scores = jdbcScoreDao.getScoreByCourseId(userId, courseId);
            if(scores.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(scores.get(0));
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'getScoreByCourseId'");
        }

    }

    @PostMapping()
    public ResponseEntity<Score> addNewScore(@RequestBody Score score){
        if(score == null){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Score newScore = jdbcScoreDao.addNewScore(score);
            if (newScore == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(newScore);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'addNewScore'");
        }
    }
}
