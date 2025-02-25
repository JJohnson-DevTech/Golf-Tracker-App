package com.techelevator.controller;


import com.techelevator.dao.JdbcTeeTimeDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.TeeTime;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/teetimes/")
public class TeeTimeController {

    private final JdbcTeeTimeDao jdbcTeeTimeDao;

    public TeeTimeController(JdbcTeeTimeDao jdbcTeeTimeDao) {
        this.jdbcTeeTimeDao = jdbcTeeTimeDao;
    }

    @GetMapping()
    public ResponseEntity<List<TeeTime>> getTeeTimes(){
        return ResponseEntity.ok(jdbcTeeTimeDao.getAllTeeTimes());
    }

    @PostMapping()
    public ResponseEntity<TeeTime> addNewTeeTime(@RequestBody TeeTime teeTime){
        if(teeTime == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            TeeTime newTeeTime = jdbcTeeTimeDao.createTeeTime(teeTime);
            if(newTeeTime == null){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(newTeeTime);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'addNewTeeTime'");
        }
    }

    @PutMapping("{teeTimeId}")
    public ResponseEntity<TeeTime> updateTeeTime(@PathVariable int teeTimeId, @RequestBody TeeTime teeTime){
        if(teeTimeId <= 0 || teeTime == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            jdbcTeeTimeDao.updateTeeTime(teeTime);
            return ResponseEntity.ok(teeTime);
        }catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'updateTeeTime'");
        }
    }

    @DeleteMapping("{teeTimeId}")
    public ResponseEntity<TeeTime> deleteTeeTime(@PathVariable int teeTimeId){
        if(teeTimeId <= 0){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            jdbcTeeTimeDao.deleteTeeTime(teeTimeId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'deleteTeeTime'");
        }
    }
}
