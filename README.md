# Rest Assured DB Validation Project

This project demonstrates how to validate REST API responses against data stored in a backend database using Rest Assured and TestNG. It includes a basic setup for a Maven project with tests that perform HTTP requests, validate the responses, and cross-check them with data from a MySQL database.

## Project Structure

rest-assured-db-validation/
├── pom.xml
└── src
├── main
│ └── resources
└── test
└── java
└── com
└── example
└── APITest.java


## Prerequisites

- Java Development Kit (JDK)
- Maven
- MySQL Database
- Git

## Setup Instructions

### Clone the Repository


git clone https://github.com/your_github_username/rest-assured-db-validation.git
cd rest-assured-db-validation

## Configure Environment Variables
### Set up the following environment variables to configure the database and API settings:
 
export JDBC_URL="jdbc:mysql://localhost:3306/yourdatabase"
export DB_USERNAME="yourusername"
export DB_PASSWORD="yourpassword"
export API_BASE_URL="https://api.example.com"
export API_BASE_PATH="/v1"

## Build Project
mvn clean install

## Run the Tests
mvn test

## NOTE: THE ABOVE PROJECT IS A VERY BASIC SIMPLE TEST FILE. PURPOSE OF THE ABOVE IS TO SHOW A QUICK EXAMPLE OF HOW PERFORM DB VALIDATIONS AFTER A REST API CALL. BY NO MEANS THIS IS FUNCTIONING, PLEASE UPDATE NECESSARY TEST FILES TO MAKE IT WORK FOR YOUR APP. 
## ALSO THIS DOES NOT HAVE A RECOMMENDED FRAMEWORK STRUCTURE, NEITHER FOLLOWS BEST PRACTICES. 