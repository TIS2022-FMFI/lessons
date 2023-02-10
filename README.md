# Lesson Learning
Function of Project Lesson Learning is to search in a database
full of information about past mistakes and problems.
User is allowed to add and remove categories, key words and problems.
Simple design helps to create an easy way to navigate around.

## Installation guide
If you have created database, mariadb-java-client.jar, exe, config and JDK/JRE, simply double-click on exe and applications start.  
Here are some instructions to build project, exe and run it.

### Build Jar
Build jar from this project. In IntelliJ Idea build artifact.

### Create exe file
1. Install Launch4J from [link](https://launch4j.sourceforge.net)
2. Create folder to save project in it
3. Download and unzip [Liberica Standard JDK 8u362+9.zip](https://bell-sw.com/pages/downloads/) to it
4. Copy lessons.jar, [mariadb-java-client-3.1.0.jar](lib/mariadb-java-client-3.1.0.jar) and [config.xml](config/config.xml) to this folder
5. Open Launch4J, add jars and generate exe using [tutorial](https://medium.com/javarevisited/creating-executable-exe-file-from-java-archive-jar-file-9e83f42baade)

### Create database
Run [create scrip](create_script.sql) on database MariaBD to create database for application. In [config.xml](config/config.xml)
change database, user, password to connect to database.

### Run 
In [config.xml](config/config.xml) change delete-password to confirm delete for lesson.
Now exe can be run by simply double-click on it or create reference to it somewhere else.
