package com.techelevator.dao;

import com.techelevator.model.Leagues;

import java.util.List;

public interface LeaguesDao {

    List<Leagues> getAllLeagues();

    String generateInviteLink(int leagueId);

    void createLeague (Leagues league);

    //TODO later date
//    void inviteUserToLeague(int leagueId, int hostId, String email);

    boolean acceptInvitation(String inviteLink, int userId);

    Leagues getLeagueById(int leagueId);

    List<Leagues> getLeaguesForUser(int userId);

    //TODO we could add this back in later but not necessary for trello functionality
//    List<Leagues> getActiveLeaguesNotFull();

    boolean joinLeague(int leagueId, int userId);

    void updateLeague(Leagues league);

    void deactivateLeague(int leagueId);


}