CREATE TABLE user_basic (
	u_id VARCHAR ( 10 ) NOT NULL PRIMARY KEY,
	gender VARCHAR ( 10 ) NOT NULL,
	status VARCHAR ( 10 ) NOT NULL,
	level VARCHAR ( 10 ) NOT NULL
);
CREATE TABLE user_edu (
	u_id VARCHAR ( 10 ) NOT NULL PRIMARY KEY,
	degree VARCHAR ( 10 ),
	school_name VARCHAR ( 255 ),
	major_str VARCHAR ( 50 ),
FOREIGN KEY ( u_id ) REFERENCES user_basic ( u_id ));
CREATE TABLE user_skill (
	u_id VARCHAR ( 10 ) NOT NULL PRIMARY KEY,
	skill_name VARCHAR ( 255 ),
FOREIGN KEY ( u_id ) REFERENCES  user_basic ( u_id ));
CREATE TABLE user_interest (
	u_id VARCHAR ( 10 ) NOT NULL PRIMARY KEY,
	interest_name VARCHAR ( 255 ),
FOREIGN KEY ( u_id ) REFERENCES user_basic ( u_id ));
CREATE TABLE article_info (
	a_id VARCHAR ( 10 ) NOT NULL PRIMARY KEY,
	type VARCHAR ( 10 ) NOT NULL,
	is_top binary ( 1 ) NOT NULL,
	status VARCHAR ( 10 ) NOT NULL ,
	domain VARCHAR ( 255 ) NOT NULL
);
CREATE TABLE user_behavior (
	u_id VARCHAR ( 10 ) NOT NULL,
	behavior VARCHAR ( 10 ) NOT NULL,
	a_id VARCHAR ( 10 ) NOT NULL,
	behavior_time datetime NOT NULL,
	PRIMARY KEY ( u_id, behavior, a_id, behavior_time ),
FOREIGN KEY ( u_id ) REFERENCES user_basic ( u_id ),
FOREIGN KEY ( a_id ) REFERENCES article_info ( a_id ));