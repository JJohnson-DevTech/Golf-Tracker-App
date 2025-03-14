package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Leagues;
import com.techelevator.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class JdbcLeaguesDao implements LeaguesDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLeaguesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Leagues> getAllLeagues() {
        String sql = "SELECT leagues.league_id, leagues.league_name, leagues.league_host, leagues.course_id, leagues.is_active, golf_courses.course_name, " +
                "league_members.member_id, leagues.min_players, users.username, users.first_name, users.last_name, users.user_id " +
                "FROM leagues " +
                "LEFT JOIN league_members ON leagues.league_id = league_members.league_id " + // Changed to LEFT JOIN
                "LEFT JOIN users ON league_members.member_id = users.user_id " + // Changed to LEFT JOIN
                "LEFT JOIN golf_courses ON leagues.course_id = golf_courses.course_id"; // Changed to LEFT JOIN
        Map<Integer, Leagues> leaguesMap = new HashMap<>();
        try {
            jdbcTemplate.query(sql, (rs) -> {
                int leagueId = rs.getInt("league_id");
                Leagues league = leaguesMap.get(leagueId);
                if (league == null) { // Check if league already exists
                    league = new Leagues(
                            leagueId,
                            rs.getString("league_name"),
                            rs.getInt("league_host"),
                            rs.getInt("course_id"),
                            rs.getBoolean("is_active"),
                            rs.getInt("min_players"),
                            rs.getString("course_name")
                    );
                    leaguesMap.put(leagueId, league); // Add new league to map
                }

                int userId = rs.getInt("user_id");
                if (!rs.wasNull()) {
                    User user = new User(
                            userId,
                            rs.getString("username"),
                            rs.getString("first_name"),
                            rs.getString("last_name")
                    );
                    if (league.getLeagueUsers() == null) {
                        league.setLeagueUsers(new ArrayList<>()); // Initialize user list if null
                    }
                    league.getLeagueUsers().add(user); // Add user to league
                }
            });
        } catch (EmptyResultDataAccessException e) {
            throw new DaoException("Nothing was returned.");
        } catch (Exception e) {
            throw new DaoException("Issue with getAllLeagues", e);
        }
        return new ArrayList<>(leaguesMap.values()); // Return list of leagues
    }

    @Override
    public String generateInviteLink(int leagueId) {
        if (leagueId <= 0) {
            throw new IllegalArgumentException("Invalid league ID for invite link");
        }

        String getHostSql = "SELECT league_host FROM leagues WHERE league_id = ?";
        Integer hostId = jdbcTemplate.queryForObject(getHostSql, Integer.class, leagueId);

        if (hostId == null) {
            throw new IllegalArgumentException("No host found for league ID: " + leagueId);
        }

        String inviteCode = UUID.randomUUID().toString();
        String baseURL = "http://localhost:9000";
        String inviteLink = baseURL + "/invite/" + inviteCode;

        //stores this invitation link in our database
        String sql = "INSERT INTO invitations (league_id, host_id, invite_link) VALUES (?, ?, ?);";
        jdbcTemplate.update(sql, leagueId, hostId, inviteLink);
        System.out.println("Generated Invite Link: " + inviteLink);
        return inviteLink;

    }

    @Override
    public int createLeague(Leagues league) {
        // Checking to make sure course exists before creating a league
        String checkForCourse = "SELECT COUNT(*) FROM golf_courses WHERE course_id = ?";
        Integer courseExists = jdbcTemplate.queryForObject(checkForCourse, Integer.class, league.getCourseId());

        if (courseExists == null || courseExists == 0) {
            throw new IllegalArgumentException("Invalid course ID: Course does not exist.");
        }

        // Insert the league and get the generated league_id
        String sql = "INSERT INTO leagues (league_name, league_host, course_id, is_active, min_players) " +
                "VALUES (?, ?, ?, ?, ? ) RETURNING league_id;";
        int leagueId;
        try {
            leagueId = jdbcTemplate.queryForObject(sql, Integer.class, league.getLeagueName(),
                    league.getLeagueHost(), league.getCourseId(),
                    league.getIsActive(), league.getMinPlayers());
        } catch (DataAccessException e) {
            System.err.println("Error inserting league: " + e.getMessage());
            throw new DaoException("Error inserting league", e);
        }

        // Insert the host or validated users into the league_members table
        String insertMemberSql = "INSERT INTO league_members (league_id, member_id) VALUES (?, ?)";
        try {
            // Add the league host to the league_members table
            jdbcTemplate.update(insertMemberSql, leagueId, league.getLeagueHost());

            // Add other validated users to the league_members table if any
            if (league.getLeagueUsers() != null) {
                for (User user : league.getLeagueUsers()) {
                    jdbcTemplate.update(insertMemberSql, leagueId, user.getId());
                }
            }
        } catch (DataAccessException e) {
            System.err.println("Error inserting league members: " + e.getMessage());
            throw new DaoException("Error inserting league members", e);
        }

        return leagueId;
    }

    @Override
    public boolean acceptInvitation(String inviteLink, int userId) {
        String sql = "SELECT league_id FROM invitations WHERE invite_link LIKE ? AND status = 'pending'";
        try {
            Integer leagueId = jdbcTemplate.queryForObject(sql, Integer.class, "%" + inviteLink);

            if (leagueId != null) {
                joinLeague(leagueId, userId);

                String updateSql = "UPDATE invitations SET status = 'accepted' WHERE invite_link = ?";
                jdbcTemplate.update(updateSql, inviteLink);

                return true;
            }
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return false;
    }

    @Override
    public Leagues getLeagueById(int leagueId) {
        String sql = "SELECT * FROM leagues WHERE league_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> new Leagues(
                            rs.getInt("league_id"),
                            rs.getString("league_name"),
                            rs.getInt("league_host"),
                            rs.getInt("course_id"),
                            rs.getBoolean("is_active"),
                            rs.getInt("min_players")
                    ), leagueId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Leagues> getLeaguesForUser(int userId) {
        // This method allows users to view leagues they are and have been a part of so they can access information even after league is over.
        // Alias's = l - leagues, lm - league_members
        String sql = "SELECT l.* FROM leagues l LEFT JOIN league_members lm ON lm.league_id = l.league_id WHERE lm.member_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Leagues(
                        rs.getInt("league_id"),
                        rs.getString("league_name"),
                        rs.getInt("league_host"),
                        rs.getInt("course_id"),
                        rs.getBoolean("is_active"),
                        rs.getInt("min_players")
                ), userId);
    }

    @Override
    public boolean joinLeague(int leagueId, int userId) {
        // Check if league is full or inactive
        String checkLeague = "SELECT min_players, is_active FROM leagues WHERE league_id = ?";
        Map<String, Object> leagueInfo = jdbcTemplate.queryForMap(checkLeague, leagueId);

        Integer minPlayers = (Integer) leagueInfo.get("min_players");
        Boolean isActive = (Boolean) leagueInfo.get("is_active");

        // League is not active or doesn't exist, cannot join
        if (minPlayers == null || isActive == null || !isActive) {
            return false;
        }

        //Get the current number of players in the league
        String playerCount = "SELECT COUNT(*) FROM league_members WHERE league_id = ?";
        Integer currentPlayers = jdbcTemplate.queryForObject(playerCount, Integer.class, leagueId);

        // If league is not full allow players to join
        if (currentPlayers < minPlayers) {
            String joinLeagueSql = "INSERT INTO league_members (league_id, member_id) VALUES (?, ?)";
            jdbcTemplate.update(joinLeagueSql, leagueId, userId);
            return true;
        }
        return false;
    }

    @Override
    public void updateLeague(Leagues league) {
        String sql = "UPDATE leagues SET league_name = ?, league_host = ?, course_id = ?, is_active = ?, min_players = ? WHERE league_id = ?";
        jdbcTemplate.update(sql, league.getLeagueName(), league.getLeagueHost(), league.getCourseId(),
                league.getIsActive(), league.getMinPlayers(), league.getLeagueId());
    }

    @Override
    public void deactivateLeague(int leagueId) {
        String sql = "UPDATE leagues SET is_active = FALSE WHERE league_id = ?";
        jdbcTemplate.update(sql, leagueId);
    }

}