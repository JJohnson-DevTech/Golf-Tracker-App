package com.techelevator.dao;

import com.techelevator.model.Leagues;

import java.sql.Timestamp;
import java.util.List;

public interface LeaguesDao {

    List<Leagues> getLeagues();

    Leagues getLeagueById(int leagueId);

    Leagues getLeagueByName(String leagueName);

    Leagues createLeague(String leagueName,int leagueHost);

    Leagues createMatchTime(Timestamp matchTime);
}
