package com.techelevator.controller;

import com.techelevator.dao.JdbcLeaguesDao;
import com.techelevator.dao.LeaguesDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Leagues;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeaguesDao leaguesDao;
    private final JdbcLeaguesDao jdbcLeaguesDao;



    public LeagueController(LeaguesDao leaguesDao, JdbcLeaguesDao jdbcLeaguesDao) {
        this.leaguesDao = leaguesDao;
        this.jdbcLeaguesDao = jdbcLeaguesDao;
    }


    @GetMapping()
    public List<Leagues> getAllLeagues(){
        return jdbcLeaguesDao.getAllLeagues();
    }

    @PostMapping()
    public ResponseEntity<Leagues> createLeague(@RequestBody Leagues league) {
        if(league == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            jdbcLeaguesDao.createLeague(league);
            return ResponseEntity.status(HttpStatus.CREATED).body(league);
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'createLeague'");
        }
    }

    @PutMapping()
    public ResponseEntity<Leagues> updateLeague(@RequestBody Leagues league) {
        try{
            jdbcLeaguesDao.updateLeague(league);
            return ResponseEntity.status(HttpStatus.OK).body(league);
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'updateLeague'");
        }
    }

    @DeleteMapping()
    public ResponseEntity<Leagues> deleteLeague(@PathVariable int leagueId){
        if(leagueId == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            jdbcLeaguesDao.deactivateLeague(leagueId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'deleteLeague'");
        }
    }

    @GetMapping(path = "/invite")
    public String generateInvite(@RequestParam int leagueId){
        if(leagueId == 0) throw new IllegalArgumentException("league id cannot be 0");
        //passes in the league id from the parameters into the dao method to create the link
        String inviteLink = jdbcLeaguesDao.generateInviteLink(leagueId);
        System.out.println(inviteLink);

        return inviteLink;
    }


}
