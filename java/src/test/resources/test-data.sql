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

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

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

INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state, datetime_reported) VALUES (-90, 12, 'pothole 1', 5, 'shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH', '1999-12-31');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state, datetime_reported) VALUES (10, 6, 'pothole 2', 3, 'road', 'Cleveland Ave', 'Linden', 'Columbus', 'OH', '2000-01-01');

COMMIT TRANSACTION;
