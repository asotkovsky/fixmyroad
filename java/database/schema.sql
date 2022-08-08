BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE potholes (
  pothole_id serial NOT NULL,
  datetime_reported timestamp DEFAULT now(),
  longitude decimal (9,6) NOT NULL,
  latitude decimal (8,6) NOT NULL,
  description varchar (200),
  severity int,
  location_on_roadway varchar (10),
  PRIMARY KEY (pothole_id)
);

INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway) VALUES (-82.983330, 39.983334, 'pothole 1', 5, 'shoulder');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway) VALUES (-82.998790, 39.961180, 'pothole 2', 3, 'road');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway) VALUES (-83.042504, 39.997804, 'pothole 3', 1, 'shoulder');

COMMIT TRANSACTION;
