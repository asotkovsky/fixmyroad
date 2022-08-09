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
  id serial NOT NULL,
  datetime_reported timestamp DEFAULT now(),
  longitude decimal (9,6) NOT NULL,
  latitude decimal (8,6) NOT NULL,
  description varchar (200),
  severity int,
  location_on_roadway varchar (10),
  road_name varchar (32),
  neighborhood varchar (32),
  city varchar (20),
  state varchar (2),

  PRIMARY KEY (id)
);

INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-82.983330, 39.983334, 'pothole 1', 5, 'shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-82.998790, 39.961180, 'pothole 2', 3, 'road', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-83.042504, 39.997804, 'pothole 3', 1, 'shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-83.002533, 39.965521, 'pothole 4', 2, 'road', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-83.014596, 39.960514, 'pothole 5', 4, 'shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');

COMMIT TRANSACTION;
