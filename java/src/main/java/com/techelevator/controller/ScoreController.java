package com.techelevator.controller;

import com.techelevator.dao.JdbcScoreDao;
import com.techelevator.dao.ScoreDao;
import com.techelevator.model.Score;
import org.apache.coyote.Response;
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

    @GetMapping("{scoreId}")
    public ResponseEntity<Score> getScoreById(@PathVariable int scoreId){
        if(scoreId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Score score = jdbcScoreDao.getScoreByScoreId(scoreId);
        if(score == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(score);
    }

    @GetMapping("league")
    public ResponseEntity<Score> getScoreByLeagueId(@RequestParam int userId, @RequestParam int leagueId){
        if( userId <= 0 || leagueId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // created this as a list but upon further thinking it would probably be unique
        List<Score> scores = jdbcScoreDao.getScoreByLeagueId(userId, leagueId);
        if(scores.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(scores.get(0));
    }

    @GetMapping("course")
    public ResponseEntity<Score> getScoreByCourseId(@RequestParam int userId, @RequestParam int courseId){
        if(userId <= 0 || courseId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<Score> scores = jdbcScoreDao.getScoreByCourseId(userId, courseId);
        if(scores.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(scores.get(0));
    }

    @PostMapping()
    public ResponseEntity<Score> addNewScore(@RequestBody Score score){
        if(score == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
            Score newScore = jdbcScoreDao.addNewScore(score);
            if (newScore == null) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        return ResponseEntity.ok(newScore);
    }
}
