package com.techelevator.services;

import com.techelevator.dao.JdbcScoreDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreService {

    private final JdbcScoreDao jdbcScoreDao;

    @Autowired
    public ScoreService(JdbcScoreDao jdbcScoreDao) {
        this.jdbcScoreDao = jdbcScoreDao;
    }


    public double calculateHandicap(int userId){
        Score score = jdbcScoreDao.getTotalOfPast5Scores(userId);

        int totalScore = score.getTotalScore();
        int totalPar = score.getPar() * 5;

        if(totalScore == 0 || totalPar == 0){
            throw new DaoException("Insufficient data to calculate handicap");
        }

        double handicap = ((totalScore - totalPar) / 5.0) * 0.8;

        return handicap;
    }
}
