BEGIN TRANSACTION;

DROP TABLE IF EXISTS user_favorites, league_members, scores, leaderboards, tee_times, leagues, golf_course, users CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	--favorite_course int,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
	--CONSTRAINT FK_favorite FOREIGN KEY (favorite_course) REFERENCES user_favorites(course_id)
);

CREATE TABLE golf_course (
    course_id SERIAL,
    club_name varchar(50) NOT NULL,
    course_name varchar(50) NOT NULL,
    address varchar(250) NOT NULL,
    city varchar(50) NOT NULL,
    state char(2) NOT NULL,
    yardage int NOT NULL,
    par int NOT NULL,
    holes int NOT NULL,
    CONSTRAINT PK_course PRIMARY KEY (course_id)
);

CREATE TABLE leagues (
    league_id SERIAL,
    league_name varchar(50) NOT NULL,
    league_host int NOT NULL,
    match_time timestamp,
    CONSTRAINT PK_leagues PRIMARY KEY (league_id)
    --CONSTRAINT FK_leagues_league_members FOREIGN KEY (league_host) REFERENCES league_members(member_id)
);

CREATE TABLE user_favorites (
    user_id int NOT NULL,
    course_id int NOT NULL,
    CONSTRAINT PK_user_golf_course PRIMARY KEY (user_id, course_id)
    --CONSTRAINT FK_user_favorites_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    --CONSTRAINT FK_user_favorites_golf_course FOREIGN KEY (favorite_id) REFERENCES golf_course(course_id)
);

CREATE TABLE league_members (
    member_id int,
    league_id int,
    CONSTRAINT PK_league_members PRIMARY KEY (member_id, league_id)
    --CONSTRAINT FK_league_members_users FOREIGN KEY (member_id) REFERENCES users(user_id),
    --CONSTRAINT FK_league_members_leagues FOREIGN KEY (league_id) REFERENCES league(league_id)
);


CREATE TABLE scores (
    score_id SERIAL,
    user_id int NOT NULL,
    course_id int NOT NULL,
    league_id int NOT NULL,
    score int NOT NULL,
    CONSTRAINT PK_scores PRIMARY KEY (score_id)
    --CONSTRAINT FK_scores_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    --CONSTRAINT FK_scores_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id),
    --CONSTRAINT FK_scores_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id)
);

CREATE TABLE leaderboards (
    leaderboard_id SERIAL,
    user_id int NOT NULL,
    course_id int NOT NULL,
    league_id int,
    total_score int NOT NULL, --computed value. Is it needed?
    ranking int NOT NULL,
    CONSTRAINT PK_leaderboards PRIMARY KEY (leaderboard_id)
    --CONSTRAINT FK_leaderboards_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    --CONSTRAINT FK_leaderboards_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id),
    --CONSTRAINT FK_leaderboards_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id)
);

CREATE TABLE tee_times (
    tee_time_id SERIAL,
    tee_off_time timestamp,
    course_id int,
    user_id int,
    league_id int,
    CONSTRAINT PK_tee_time PRIMARY KEY (tee_time_id)
    --CONSTRAINT FK_tee_times_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id),
    --CONSTRAINT FK_tee_times_users FOREIGN KEY (user_id) REFERENCES users(user_id),
    --CONSTRAINT FK_tee_times_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id)
);


--ALTER TABLE leagues ADD CONSTRAINT FK_leagues_league_members FOREIGN KEY (league_host) REFERENCES league_members(member_id);
ALTER TABLE user_favorites ADD CONSTRAINT FK_user_golf_course_users FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE user_favorites ADD CONSTRAINT FK_user_golf_course_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id);
ALTER TABLE league_members ADD CONSTRAINT FK_league_members_users FOREIGN KEY (member_id) REFERENCES users(user_id);
ALTER TABLE league_members ADD CONSTRAINT FK_league_members_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id);
ALTER TABLE scores ADD CONSTRAINT FK_scores_users FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE scores ADD CONSTRAINT FK_scores_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id);
ALTER TABLE scores ADD CONSTRAINT FK_scores_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id);
ALTER TABLE leaderboards ADD CONSTRAINT FK_scores_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id);
ALTER TABLE leaderboards ADD CONSTRAINT FK_leaderboards_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id);
ALTER TABLE leaderboards ADD CONSTRAINT FK_leaderboards_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id);
ALTER TABLE tee_times ADD CONSTRAINT FK_tee_times_golf_course FOREIGN KEY (course_id) REFERENCES golf_course(course_id);
ALTER TABLE tee_times ADD CONSTRAINT FK_tee_times_users FOREIGN KEY (user_id) REFERENCES users(user_id);
ALTER TABLE tee_times ADD CONSTRAINT FK_tee_times_leagues FOREIGN KEY (league_id) REFERENCES leagues(league_id);




--ROLLBACK;

COMMIT TRANSACTION;
