create database if not exists lesson_learning;
use lesson_learning;

truncate table category;
truncate table key_word_problem;
SET foreign_key_checks=0;
truncate table key_word;
truncate table problem;
SET foreign_key_checks=1;

-- name
drop table if exists name cascade;
create table name (name varchar(225));
insert into name (name)
values	('Hoffman'), ('Haris'), ('Frank'), ('Owen'), ('Ward'), ('Brokolica'),
          ('Waters'), ('Lawson'), ('Quinn'), ('King'), ('Medina'),
          ('Burger'), ('Joseph'), ('Phillips'), ('Samsung'), ('Tyler'),
          ('Newton'), ('Dunn'), ('Figueroa'), ('Bull'), ('Cunningham'),
          ('Webster'), ('Kowalski'), ('Wazovski'), ('Catchem'), ('Ford'),
          ('Lavigne'), ('Chill'), ('Jill'), ('Boy-ler'), ('Gibbon'),
          ('Anakonda'), ('Lawrence'), ('McDonald'), ('Komar'), ('Mucha'),
          ('Loveson'), ('Young'), ('Norris'), ('Downie'), ('Bailando'),
          ('Feetstrong'), ('Casper'), ('Rodrigez'), ('Pitbull'), ('Bright'),
          ('Joke'), ('Karafiat'), ('Ananas'), ('Melon'), ('Brokolica');

insert into category (title)
values ('hliník'), ('železo'), ('plast'), ('PVC'), ('plastelína'),
       ('sklo'), ('keramika'), ('zlato');

insert into key_word (title, prime)
values ('prasklina', true), ('škrabanec', true), ('roztavenie', true), ('zmrštenie', true);

insert into problem (title, description, path, user_name, create_at)
values ('The Dilemma of Overcrowding in Cities', 'je to pokazene', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
('The Growing Problem of Income Inequality', 'aj toto sa pokazilo', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
('The Environmental Consequences of Climate Change', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
('The Challenges of Providing Access to Quality Healthcare', 'aaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
('The Struggle for Racial and Social Justice', 'bbb', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
('The Impact of Technology on Employment and the Economy', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
('The Dangers of Cybersecurity Breaches and Data Privacy', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Difficulty of Maintaining Mental Health in a Fast-Paced World', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Impact of Overconsumption on Natural Resources and the Environment', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Challenges of Globalization and Cultural Homogenization', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Dilemma of Limited Resources', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Conundrum of Overpopulation', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Paradox of Climate Change', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Enigma of Income Inequality', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Quandary of Globalization', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Predicament of Terrorism', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Dilemma of Human Rights Violations', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Paradox of Advancing Technology', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Conundrum of Political Corruption', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now()),
 ('The Quandary of Mental Health Stigma', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now());

drop table name;

