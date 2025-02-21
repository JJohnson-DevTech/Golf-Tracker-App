package com.techelevator.controller;

import com.techelevator.dao.LeaguesDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LeagueController {

    private final LeaguesDao leaguesDao;



    public LeagueController(LeaguesDao leaguesDao) {
        this.leaguesDao = leaguesDao;
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
