#PT-BR
# **Aplicação CRUD de planetas de Star Wars**
## Introdução
Esta é uma aplicação web Java construída usando JavaServer Faces (JSF) e o framework PrimeFaces. A aplicação fornece uma funcionalidade CRUD (Create, Read, Update, Delete) para planetas de Star Wars. Os dados dos planetas são armazenados em um banco de dados MySQL.

## Requisitos
Para executar a aplicação, você precisará de:

Java Development Kit (JDK) 11 ou posterior

MySQL 8.x

Apache Maven 3.x

## Configuração

1.Clone o repositório para o seu computador.

2.Crie um novo esquema MySQL chamado "starwars".

3.Execute o seguinte script SQL no esquema "starwars" para criar a tabela de planetas e inserir alguns dados de exemplo:

	CREATE TABLE starwars.planets (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	climate VARCHAR(255) NOT NULL,
	terrain VARCHAR(255) NOT NULL,
	PRIMARY KEY (id));

INSERT INTO planets (id, name, climate, terrain) VALUES (1, "Tatooine", "árido", "deserto");
INSERT INTO planets (id, name, climate, terrain) VALUES (2, "Alderaan", "temperado", "planícies, montanhas");

4.Abra um prompt de comando ou terminal e navegue até o diretório raiz do projeto.

5.Execute o comando mvn clean package para construir a aplicação e criar um arquivo WAR.

6.Implante o arquivo WAR em um servidor web ou execute a aplicação localmente usando o servidor Jetty incorporado, executando o comando mvn jetty:run.

## Uso
Após implantar e executar a aplicação, abra um navegador da web e navegue até http://localhost:8080/starwars. Isso o levará para a página principal da aplicação onde você pode visualizar, adicionar, editar e excluir planetas.

#EN
# **Star Wars Planets CRUD Application**
## Introduction
This is a Java web application built using JavaServer Faces (JSF) and PrimeFaces framework. The application provides a CRUD (Create, Read, Update, Delete) functionality for Star Wars planets. The planets data is stored in a MySQL database.

## Requirements
To run the application, you will need:

Java Development Kit (JDK) 11 or later

MySQL 8.x

Apache Maven 3.x

## Setup

1.Clone the repository to your local machine.

2.Create a new MySQL schema named starwars.

3.Run the following SQL script in the starwars schema to create the planets table and insert some sample data:

  CREATE TABLE starwars.planets (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `climate` VARCHAR(255) NOT NULL,
    `terrain` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`));

  INSERT INTO planets (id, name, climate, terrain) VALUES (1, "Tatooine", "arid", "desert");
  INSERT INTO planets (id, name, climate, terrain) VALUES (2, "Alderaan", "temperate", "grasslands, mountains");

4.Open a command prompt or terminal and navigate to the project root directory.

5.Run the command mvn clean package to build the application and create a WAR file.

6.Deploy the WAR file to a web server or run the application locally using the embedded Jetty server by running the command mvn jetty:run.

## Usage
After deploying and running the application, open a web browser and navigate to http://localhost:8080/starwars. This will take you to the main page of the application where you can view, add, edit, and delete planets.
