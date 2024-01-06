DROP SCHEMA IF EXISTS user_db;

CREATE SCHEMA user_db;
USE user_db;

CREATE TABLE user (
	user_id CHAR(36) PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	email_id VARCHAR(100) NOT NULL UNIQUE,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	date_of_birth DATE NOT NULL,
	gender VARCHAR(10),
	password VARCHAR(60) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP SCHEMA IF EXISTS profile_db;

CREATE SCHEMA profile_db;
USE profile_db;

CREATE TABLE profile (
	user_id CHAR(36) PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	profile_image BLOB,
	bio LONGTEXT,
	cover_image BLOB,
	city VARCHAR(50),
	country VARCHAR(50),
	designation VARCHAR(50),
	company VARCHAR(50),
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP SCHEMA IF EXISTS friendrequest_db;

CREATE SCHEMA friendrequest_db;
USE friendrequest_db;

CREATE TABLE friend_request (
    request_id CHAR(36) PRIMARY KEY NOT NULL,
    sender_id CHAR(36) NOT NULL,
    reciever_id CHAR(36) NOT NULL,
    status ENUM('PENDING', 'ACCEPTED', 'DECLINED') NOT NULL,
    timestamp_sent TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    timestamp_accepted TIMESTAMP
);

DROP SCHEMA IF EXISTS friend_db;

CREATE SCHEMA friend_db;
USE friend_db;

CREATE TABLE friend (
    friendship_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    friend_id CHAR(36) NOT NULL,
    is_unfollowed BOOLEAN,
    is_blocked BOOLEAN,
    category CHAR(50)
);

DROP SCHEMA IF EXISTS post_db;

CREATE SCHEMA post_db;
USE post_db;

CREATE TABLE Post (
    post_id CHAR(36) PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    content TEXT NOT NULL,
    media_type VARCHAR(50),
	media_url VARCHAR(256),
    media_content LONGBLOB,
    timestamp TIMESTAMP NOT NULL
);


CREATE TABLE Likes (
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

CREATE TABLE Unlike (
    unlike_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

CREATE TABLE Comment (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    post_id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    content TEXT NOT NULL,
    timestamp TIMESTAMP NOT NULL
);