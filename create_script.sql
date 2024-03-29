create database if not exists lesson_learning collate = utf8mb4_unicode_ci;
use lesson_learning;

drop table if exists key_word_problem cascade ;
drop table if exists key_word cascade ;
drop table if exists problem cascade ;
drop table if exists category cascade ;

create table category (category_id int not null unique auto_increment primary key,
                       title varchar(225) not null unique );

create table key_word(key_word_id int not null unique auto_increment primary key,
                      title varchar(225) not null unique,
                      prime boolean default false);

create table problem(problem_id int not null unique auto_increment primary key ,
                     title varchar(225) not null,
                     description text,
                     path text,
                     image1 varchar(255) default null,
                     image2 varchar(255) default null,
                     category_id int not null,
                     FOREIGN KEY (category_id) REFERENCES category(category_id),
                     user_name varchar(100) not null,
                     create_at date default NOW(),
                     last_editor varchar(100) default null,
                     last_edited_at date default null,
                     edit_description varchar(500) default null);

create table key_word_problem(key_word_problem_id int not null unique auto_increment primary key,
                              problem_id int not null,
                              key_word_id int not null,
                              FOREIGN KEY (problem_id) REFERENCES problem(problem_id),
                              FOREIGN KEY (key_word_id) REFERENCES key_word(key_word_id));

ALTER TABLE key_word_problem ADD UNIQUE `unique_index`(problem_id, key_word_id);
