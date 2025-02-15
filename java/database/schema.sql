BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username varchar(50) NOT NULL UNIQUE,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL
);

CREATE TABLE golf_courses (
    id SERIAL PRIMARY KEY,
    course_name varchar(75) NOT NULL,
    city varchar(50) NOT NULL,
    state_ab char(2) NOT NULL,
    yardage int NOT NULL,
    par int NOT NULL,
    holes int DEFAULT 18 NOT NULL
);

CREATE TABLE user_favorites (
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (user_id, course_id),
    CONSTRAINT fk_user_fav FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_fav_course FOREIGN KEY (course_id) REFERENCES golf_courses(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE leagues (
    id SERIAL PRIMARY KEY,
    league_name varchar(75) NOT NULL,
    league_host INT NOT NULL,
    match_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_league_host FOREIGN KEY (league_host) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE league_members (
    member_id INT NOT NULL,
    league_id INT NOT NULL,
    PRIMARY KEY (member_id, league_id),
    CONSTRAINT fk_league_member_user FOREIGN KEY (member_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_league_member_league FOREIGN KEY (league_id) REFERENCES leagues(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE scores (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    league_id INT,
    total_score INT NOT NULL,
    CONSTRAINT fk_scores_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_scores_course FOREIGN KEY (course_id) REFERENCES golf_courses(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_scores_league FOREIGN KEY (league_id) REFERENCES leagues(id) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE leaderboards (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    league_id INT,
    total_score INT NOT NULL,
    ranking INT NOT NULL,
    CONSTRAINT fk_leaderboards_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_leaderboards_course FOREIGN KEY (course_id) REFERENCES golf_courses(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_leaderboards_league FOREIGN KEY (league_id) REFERENCES leagues(id) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE tee_times (
    id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    user_id INT NOT NULL,
    league_id INT,
    tee_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_tee_times_course FOREIGN KEY (course_id) REFERENCES golf_courses(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tee_times_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_tee_times_league FOREIGN KEY (league_id) REFERENCES leagues(id) ON DELETE SET NULL ON UPDATE CASCADE
);


COMMIT TRANSACTION;