create database if not exists lesson_learning;
use lesson_learning;

drop table if exists key_word_problem cascade ;
drop table if exists category cascade ;
drop table if exists key_word cascade ;
drop table if exists problem cascade ;

create table category (category_id int not null unique auto_increment primary key,
                       title varchar(225) not null unique );


create table key_word(key_word_id int not null unique auto_increment primary key,
                      title varchar(225) not null unique );

create table problem(problem_id int not null unique auto_increment primary key ,
                     title varchar(225) not null,
                     description varchar(1000),
                     path varchar(225) not null,
                     user_name varchar(100) not null,
                     create_at date not null,
                     last_editor varchar(100),
                     last_edited_at date);

create table key_word_problem(id int not null unique auto_increment primary key,
                              problem_id int not null,
                              key_word_id int not null,
                              FOREIGN KEY (problem_id) REFERENCES problem(problem_id),
                              FOREIGN KEY (key_word_id) REFERENCES key_word(key_word_id));
