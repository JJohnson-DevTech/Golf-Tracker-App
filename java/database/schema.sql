BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, golf_courses, user_favorites, leagues, league_members, scores, leaderboards, tee_times;

CREATE TABLE users (
	user_id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL
);

CREATE TABLE golf_courses (
    course_id SERIAL PRIMARY KEY,
    club_name varchar(75) NOT NULL,
    course_name varchar(75) NOT NULL,
    address TEXT,
    city varchar(50) NOT NULL,
    state_ab char(2) NOT NULL,
    country varchar(30) NOT NULL,
    par int DEFAULT 72
);

CREATE TABLE user_favorites (
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (user_id, course_id),
    CONSTRAINT fk_user_fav FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_fav_course FOREIGN KEY (course_id) REFERENCES golf_courses(course_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE leagues (
    league_id SERIAL PRIMARY KEY,
    league_name varchar(75) NOT NULL,
    league_host INT NOT NULL,
    course_id INT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    min_players INT DEFAULT 4,
    CONSTRAINT fk_league_host FOREIGN KEY (league_host) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES golf_courses(course_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE league_members (
    member_id INT NOT NULL,
    league_id INT NOT NULL,
    PRIMARY KEY (member_id, league_id),
    CONSTRAINT fk_league_member_user FOREIGN KEY (member_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_league_member_league FOREIGN KEY (league_id) REFERENCES leagues(league_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE invitations (
    invitation_id SERIAL PRIMARY KEY,
    league_id INT NOT NULL,
    host_id INT NOT NULL,
    invite_link VARCHAR(150) UNIQUE,
    CONSTRAINT fk_league_invite_id FOREIGN KEY (league_id) REFERENCES leagues(league_id),
    CONSTRAINT fk_host_invite_id FOREIGN KEY (host_id) REFERENCES users(user_id)
);

CREATE TABLE tee_times (
    tee_time_id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    user_id INT NOT NULL,
    league_id INT,
    tee_time TIMESTAMP NOT NULL,
    num_players INT NOT NULL,
    CONSTRAINT fk_tee_times_course FOREIGN KEY (course_id) REFERENCES golf_courses(course_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tee_times_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tee_times_league FOREIGN KEY (league_id) REFERENCES leagues(league_id) ON DELETE SET NULL ON UPDATE CASCADE
);

COMMIT TRANSACTION;