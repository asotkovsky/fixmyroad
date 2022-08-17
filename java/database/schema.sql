BEGIN TRANSACTION;

DROP TABLE IF EXISTS potholes CASCADE;
DROP TABLE IF EXISTS pothole_status CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS status CASCADE;

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
	username varchar(90) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
  );
  CREATE TABLE "image_url"(
  	"id" serial NOT NULL,
  	"pothole_id" int NOT NULL,
  	"url" varchar(256) NOT NULL,
  	 CONSTRAINT "FK_image_url.pothole_id"
     FOREIGN KEY ("pothole_id")
     REFERENCES "potholes"("id")
  );

CREATE TABLE "pothole_status" (
	"id" serial NOT NULL,
  "pothole_id" int NOT NULL,
  "status_id" int NOT NULL,
  "date" timestamp DEFAULT now() NOT NULL,
  "user_id" int NOT NULL,
  CONSTRAINT "FK_pothole_status.pothole_id"
    FOREIGN KEY ("pothole_id")
      REFERENCES "potholes"("id"),
  CONSTRAINT "FK_pothole_status.status_id"
    FOREIGN KEY ("status_id")
      REFERENCES "status"("id"),
  CONSTRAINT "FK_pothole_status.user_id"
    FOREIGN KEY ("user_id")
      REFERENCES "users"("user_id"),
	primary key ("id")
);

CREATE INDEX "PK FK" ON  "pothole_status" ("pothole_id", "status_id");

INSERT INTO users (username,password_hash,role) VALUES ('user@fixmyroad.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin@fixmyroad.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO status (status_name) VALUES ('Reported'), ('Scheduled For Inspection'), ('Inspected'), ('Scheduled For Repair'), ('Repaired'), ('Noticed');

INSERT INTO potholes (longitude, latitude, description, severity, location_on_roadway, road_name, neighborhood, city, state) VALUES
(-83.078802, 40.001482, 'pothole 1', 5, 'Shoulder', 'Greycliff Ln', 'West Scioto', 'Columbus', 'OH'),
(-82.978890, 39.994907, 'pothole 2', 3, 'Road', 'Chittenden Ave', 'South Linden', 'Columbus', 'OH'),
(-82.980136, 39.945062, 'pothole 3', 1, 'Shoulder', 'Stanley Ave', 'South Columbus', 'Columbus', 'OH'),
(-82.953489, 39.989120, 'pothole 4', 2, 'Road', 'Woodward Ave', 'St. Marys', 'Columbus', 'OH'),
(-83.045156, 39.933069, 'pothole 5', 4, 'Shoulder', 'Forest Dr', 'Southwest Columbus', 'Columbus', 'OH');


INSERT INTO pothole_status (pothole_id, status_id, date, user_id) VALUES (1,1, CURRENT_TIMESTAMP, 1), (2,1, CURRENT_TIMESTAMP, 1),
(3,1, CURRENT_TIMESTAMP, 1),
(4,1, CURRENT_TIMESTAMP, 1),
(5,1, CURRENT_TIMESTAMP, 1), (1,2, CURRENT_TIMESTAMP, 2), (1,3, CURRENT_TIMESTAMP, 2);

INSERT INTO image_url (pothole_id,url) VALUES
(1,'http://cdn.shopify.com/s/files/1/0597/4910/5840/articles/pothole_key.jpg?v=1635187694'),
(2,'https://image.shutterstock.com/z/stock-photo-car-wheel-close-up-near-a-pothole-on-the-road-broken-asphalt-on-the-roads-spring-and-autumn-1892815027.jpg'),
(3,'https://image.shutterstock.com/image-photo/potholes-road-looking-like-alien-260nw-1014358009.jpg'),
(4,'https://www.chicagomag.com/wp-content/archive/Chicago-Magazine/March-2014/How-to-Navigate-the-Red-Tape-of-Pothole-Damage-Reimbursement/Pothole.jpg'),
(5,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSH64He9salvEqpv33CuFIOeS2522ZIT_OZ1g&usqp=CAU');

COMMIT TRANSACTION;
