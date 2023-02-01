create database if not exists lesson_learning;
use lesson_learning;


truncate table key_word_problem;
SET foreign_key_checks=0;
truncate table key_word;
truncate table problem;
truncate table category;
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
       ('sklo'), ('keramika'), ('zlato'), ('olovo'), ('platina');

insert into key_word (title, prime)
values ('prasklina', true), ('škrabanec', true), ('roztavenie', true), ('zmrštenie', true), ('teplota', true),
       ('drevo', false), ('okno', false), ('korózia', false), ('ulomenie', false), ('roztrhnutie', false), ('nevim', false);

# insert into problem (title, description, path, image1, image2, user_name, create_at, category_id)
# values ('The Dilemma of Overcrowding in Cities', 'je to pokazene', 'C://user', 'main/img/obr1_Audi_A4_section.jpg', 'main/img/obr2_clearance.jpg', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1));

insert into problem (title, description, path, user_name, create_at, category_id)
values
('The Growing Problem of Income Inequality', 'aj toto sa pokazilo', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(),(SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
('The Environmental Consequences of Climate Change', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
('The Challenges of Providing Access to Quality Healthcare', 'aaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
('The Struggle for Racial and Social Justice', 'bbb', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
('The Impact of Technology on Employment and the Economy', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
('The Dangers of Cybersecurity Breaches and Data Privacy', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Difficulty of Maintaining Mental Health in a Fast-Paced World', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Impact of Overconsumption on Natural Resources and the Environment', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Challenges of Globalization and Cultural Homogenization', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Dilemma of Limited Resources', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Conundrum of Overpopulation', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Paradox of Climate Change', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Enigma of Income Inequality', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Quandary of Globalization', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Predicament of Terrorism', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Dilemma of Human Rights Violations', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Paradox of Advancing Technology', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Conundrum of Political Corruption', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('The Quandary of Mental Health Stigma', 'aaaaa', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Inconsistent Quality in Product Manufacturing', 'fjhgbjnkhbg', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Frequent Customer Complaints About Service', 'sdfgjkl', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Inefficient Processes Leading to Delays', 'asdfghjk', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Inaccurate Inventory Tracking Causing Stockouts', 'qwertzu', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('High Employee Turnover Rate', 'sdfghj', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Lack of Communication Between Departments', 'xfgzuj', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Ineffective Marketing Strategies Leading to Low Sales', 'sdfghjk', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Poor Customer Satisfaction Scores', 'sdugihk', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Lack of Training and Development Opportunities for Employees', 'sertzuik', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1)),
 ('Difficulty Retaining Talent and Attracting New Employees', 'xdz', 'C://user', (SELECT name.name from name ORDER BY RAND() LIMIT 1), now(), (SELECT category.category_id from category ORDER BY RAND() LIMIT 1));

INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));
INSERT IGNORE INTO key_word_problem(problem_id, key_word_id) values ((SELECT problem.problem_id from problem order by RAND() LIMIT 1), (SELECT key_word.key_word_id from key_word order by RAND() LIMIT 1));

drop table name;
