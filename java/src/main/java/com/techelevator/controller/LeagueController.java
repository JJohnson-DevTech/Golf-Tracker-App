package com.techelevator.controller;

import com.techelevator.dao.JdbcLeaguesDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Leagues;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> createLeague(@RequestBody Leagues league) {
        if(league == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "League data is required"));
        }
        try{
            int leagueId = jdbcLeaguesDao.createLeague(league);

            // Generate an invitation link using the returned leagueId
            String inviteLink = jdbcLeaguesDao.generateInviteLink(leagueId);

            // Create a response object with league details and invite link
            Map<String, Object> response = new HashMap<>();
            response.put("leagueId", leagueId);
            response.put("leagueName", league.getLeagueName());
            response.put("inviteLink", inviteLink);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e) {
            // Log SQL-related issues
            System.err.println("Database error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Database error occurred"));
        } catch (Exception e) {
            // Log any unexpected issues
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Unexpected server error"));
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

    @GetMapping("/invite/{inviteCode}")
    public ResponseEntity<String> acceptInvite(@PathVariable String inviteCode, @RequestParam int userId) {
        boolean success = jdbcLeaguesDao.acceptInvitation(inviteCode, userId);

        if (success) {
            return ResponseEntity.ok("Successfully joined the league!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired invitation.");
        }
    }
}
