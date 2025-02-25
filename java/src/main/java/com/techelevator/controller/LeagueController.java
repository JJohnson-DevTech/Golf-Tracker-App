package com.techelevator.controller;

import com.techelevator.dao.JdbcLeaguesDao;
import com.techelevator.dao.LeaguesDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Leagues;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/leagues")
public class LeagueController {

    private final JdbcLeaguesDao jdbcLeaguesDao;



    public LeagueController(JdbcLeaguesDao jdbcLeaguesDao) {
        this.jdbcLeaguesDao = jdbcLeaguesDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Leagues> getAllLeagues(){
        return jdbcLeaguesDao.getAllLeagues();
    }


    @GetMapping("/{leagueId}")
    public ResponseEntity<Leagues> getLeagueById(@PathVariable int leagueId){
        if(leagueId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            Leagues league = jdbcLeaguesDao.getLeagueById(leagueId);
            if(league == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(league);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'deleteLeague'");
        }
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

    @PutMapping("/{leagueId}")
    public ResponseEntity<Leagues> updateLeague(@PathVariable int leagueId, @RequestBody Leagues league) {
        if(leagueId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(league == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        try{
            jdbcLeaguesDao.updateLeague(league);
            return ResponseEntity.status(HttpStatus.OK).body(league);
        }   catch (Exception e){
            throw new DaoException("Issue with controller method 'updateLeague'");
        }
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<Leagues> deleteLeague(@PathVariable int leagueId){
        if(leagueId <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try{
            jdbcLeaguesDao.deactivateLeague(leagueId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new DaoException("Issue with controller method 'deleteLeague'");
        }

    }

    @GetMapping(path = "/invite")
    //using requestparam here requires us to input a query string, likely
    // /invite?league_id=x
    public String generateInvite(@RequestParam int leagueId) {
        if (leagueId == 0) throw new IllegalArgumentException("league id cannot be 0");
        //passes in the league id from the parameters into the dao method to create the link
        String inviteLink = jdbcLeaguesDao.generateInviteLink(leagueId);
        System.out.println(inviteLink);
        return inviteLink;
    }
}
