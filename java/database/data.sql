BEGIN TRANSACTION;

INSERT INTO users (first_name,last_name,username,password_hash,role) VALUES ('Henry', 'Edwards','user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (first_name,last_name,username,password_hash,role) VALUES ('Mimi', 'Malone','admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

COMMIT TRANSACTION;
