# star-wars-planet-crud
Projeto de CRUD utilizando JSF + Primefaces com get na api https://swapi.dev/about
`# **Star Wars Planets CRUD Application**`
`## Introduction`
This is a Java web application built using JavaServer Faces (JSF) and PrimeFaces framework. The application provides a CRUD (Create, Read, Update, Delete) functionality for Star Wars planets. The planets data is stored in a MySQL database.

`## Requirements`
To run the application, you will need:

Java Development Kit (JDK) 11 or later
MySQL 8.x
Apache Maven 3.x
`## Setup`
`1`Clone the repository to your local machine.
`2`Create a new MySQL schema named starwars.
`3`Run the following SQL script in the starwars schema to create the planets table and insert some sample data:
sql
Copy code
```
CREATE TABLE `starwars`.`**planets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `climate` VARCHAR(255) NOT NULL,
  `terrain` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO planets (id, name, climate, terrain) VALUES (1, "Tatooine", "arid", "desert");
INSERT INTO planets (id, name, climate, terrain) VALUES (2, "Alderaan", "temperate", "grasslands, mountains");
```
`4`Open a command prompt or terminal and navigate to the project root directory.
`5`Run the command mvn clean package to build the application and create a WAR file.
`6`Deploy the WAR file to a web server or run the application locally using the embedded Jetty server by running the command mvn jetty:run.
`## Usage`
After deploying and running the application, open a web browser and navigate to http://localhost:8080/starwars. This will take you to the main page of the application where you can view, add, edit, and delete planets.
