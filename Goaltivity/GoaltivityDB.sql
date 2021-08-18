create database if not exists goaltivity;
use goaltivity;
drop table if exists journal_tags;
drop table if exists journal;
drop table if exists tags;
drop table if exists user;
-- drop table if exists reminders;
-- drop table if exists events;
create table user (
	  id int auto_increment not null,
    firstname varchar(20) not null,
    lastname varchar(30) not null,
    emailaddress varchar(100) not null,
    primary key (id)
);
create table journal (
	  id int auto_increment not null,
    date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    title varchar(256) not null,
    content text not null,
    user int not null,
    primary key (id),
    foreign key (user) references user(id)
);
-- Diary, Travel, Work, Computer, Photo, Lifestyle, General
create table tags (
	id int auto_increment not null,
  name varchar(20) NOT NULL,
  primary key (id)
);
-- A journal can have multiple tags associated with it.
create table journal_tags(
  journal int NOT NULL,
  tag int NOT NULL,
  id int NOT NULL auto_increment,
  primary key(id),
 -- foreign key(journal) references journal(id),
  foreign key(tag) references tags(id)
);
INSERT INTO tags (name) VALUES("Diary"),("Travel"), ("Work"), ("Computer"), ("Photo"), ("Lifestyle"), ("General");
SELECT * FROM tags;
Select * from journal_tags;