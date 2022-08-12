BEGIN TRANSACTION;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE "potholes" (
  "id" serial NOT NULL,
  "longitude" Decimal (9,6) NOT NULL,
  "latitude" Decimal (8,6) NOT NULL,
  "neighborhood" varchar (32),
  "city" varchar (20),
  "state" varchar (2),
  "road_name" varchar (32),
  "description" varchar (200),
  "severity" int,
  "location_on_roadway" varchar (10),
  PRIMARY KEY ("id")
);

CREATE TABLE "status" (
  "id" serial  NOT NULL,
  "status_name" varchar (32),
  PRIMARY KEY ("id")
);

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
  );

CREATE TABLE "pothole_status" (
  "pothole_id" int NOT NULL,
  "status_id" int NOT NULL,
  "date" timestamp DEFAULT now(),
  "user_id" int NOT NULL,
  CONSTRAINT "FK_pothole_status.pothole_id"
    FOREIGN KEY ("pothole_id")
      REFERENCES "potholes"("id"),
  CONSTRAINT "FK_pothole_status.status_id"
    FOREIGN KEY ("status_id")
      REFERENCES "status"("id"),
  CONSTRAINT "FK_pothole_status.user_id"
    FOREIGN KEY ("user_id")
      REFERENCES "users"("user_id")
);

CREATE INDEX "PK FK" ON  "pothole_status" ("pothole_id", "status_id");

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO status (status_name) VALUES ('Reported'), ('Scheduled For Inspection'), ('Inspected'), ('Scheduled For Repair'), ('Repaired');

INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-82.983330, 39.983334, 'pothole 1', 5, 'Shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-82.998790, 39.961180, 'pothole 2', 3, 'Road', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-83.042504, 39.997804, 'pothole 3', 1, 'Shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-83.002533, 39.965521, 'pothole 4', 2, 'Road', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');
INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES (-83.014596, 39.960514, 'pothole 5', 4, 'Shoulder', 'Cleveland Ave', 'Linden', 'Columbus', 'OH');

INSERT INTO pothole_status (pothole_id, status_id, date, user_id) VALUES (1,1, CURRENT_TIMESTAMP, 1), (2,1, CURRENT_TIMESTAMP, 1),
(3,1, CURRENT_TIMESTAMP, 1),
(4,1, CURRENT_TIMESTAMP, 1),
(5,1, CURRENT_TIMESTAMP, 1), (1,2, CURRENT_TIMESTAMP, 2), (1,3, CURRENT_TIMESTAMP, 2);
COMMIT TRANSACTION;
