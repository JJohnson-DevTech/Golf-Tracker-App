package com.techelevator.dao;

import com.techelevator.model.Leagues;

import java.util.List;

public interface LeaguesDao {

    void createLeague (Leagues league);

    void inviteUserToLeague(int leagueId, int hostId, String email);

    boolean acceptInvitation(String inviteLink, int userId);

    Leagues getLeagueById(int leagueId);

    List<Leagues> getLeaguesForUser(int userId);

    List<Leagues> getActiveLeaguesNotFull();

    boolean joinLeague(int leagueId, int userId);

    void updateLeague(Leagues league);

    void deactivateLeague(int leagueId);
}