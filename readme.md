# OC Projet 3

This project is a rental API.

## Technologies

This project is made with Spring Boot 3 and Java 21 and is using Maven as project manager.
Postgresql 15 is used as DBMS.

## Create the database

A database named `ocp3` needs to be created in postgres. 
Use the file `script.sql` in the resources folder to create the database, the user and the tables.

## Run the project

Compile the project with maven and run it. The application will be running on `localhost:3001`.

## Project architecture

This project is following the package by feature architecture.
Thus, packages are based on their feature or functionalities.

- `application` Contains the code to make the application work
  - `api_documentation` Documentation of the API
  - `common` Common code (response dto)
  - `configuration` Configuration of the application
  - `errors` Error handling
  - `security` Security of the API
- `domains` Contains features
  - `auth` Authentication feature
  - `message` Message feature
  - `rental` Rental feature
  - `upload` Upload feature
  - `user` User feature

## Access the documentation

To access the Swagger documentation :
- UI : http://localhost:3001/api/swagger-ui/index.html
- Json : http://localhost:3001/api/v3/api-docs
