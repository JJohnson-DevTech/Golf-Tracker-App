package com.techelevator.controller;

import com.techelevator.dao.JdbcLeaguesDao;
import com.techelevator.dao.LeaguesDao;
import com.techelevator.model.Leagues;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
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

    @GetMapping(path = "/invite")
    public String generateInvite(@RequestParam int leagueId){
        if(leagueId == 0) throw new IllegalArgumentException("league id cannot be 0");
        //passes in the league id from the parameters into the dao method to create the link
        String inviteLink = leaguesDao.generateInviteLink(leagueId);
        System.out.println(inviteLink);

        return inviteLink;
    }


}
