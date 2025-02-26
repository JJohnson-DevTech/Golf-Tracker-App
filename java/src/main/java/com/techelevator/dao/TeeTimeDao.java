package com.techelevator.dao;

import com.techelevator.model.TeeTime;

import java.util.List;

public interface TeeTimeDao {

    List<TeeTime> getAllTeeTimes();

    List<TeeTime> getUpcomingTeeTimes();

    TeeTime createTeeTime(TeeTime teeTime);


    boolean activeMatch(TeeTime teeTime);

    TeeTime getTeeTimeById(int teeTimeId);

    TeeTime getTeeTimeByLeagueId(int leagueId);

    TeeTime getTeeTimeByCourseId(int courseId);

    void updateTeeTime(TeeTime teeTime);

    void deleteTeeTime(int teeTimeId);

    // TODO vv
    //possibly implement a join Tee Time similar to LeagueDao?
    // TODO ^^
}
