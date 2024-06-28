package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class APITest {

    private Connection connection;

    @BeforeClass
    public void setup() {
        // Setup Rest Assured
        RestAssured.baseURI = "https://api.example.com";
        RestAssured.basePath = "/v1";

        // Setup Database Connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/yourdatabase";
        String username = "yourusername";
        String password = "yourpassword";
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validateGetResponseWithDB() {
        // Perform GET Request
        Response response = 
            given()
                .contentType("application/json")
            .when()
                .get("/endpoint")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("example"))
                .extract()
                .response();

        // Extract data from response
        int id = response.path("id");
        String name = response.path("name");

        // Query Database
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table WHERE id = " + id);
            if (resultSet.next()) {
                int dbId = resultSet.getInt("id");
                String dbName = resultSet.getString("name");

                // Validate response with database data
                assert id == dbId;
                assert name.equals(dbName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void validatePostResponseWithDB() {
        String requestBody = "{ \"key1\": \"value1\", \"key2\": \"value2\" }";
        
        // Perform POST Request
        Response response = 
            given()
                .contentType("application/json")
                .body(requestBody)
            .when()
                .post("/endpoint")
            .then()
                .statusCode(201)
                .body("success", equalTo(true))
                .extract()
                .response();

        // Extract data from response
        int id = response.path("data.id");
        String key1 = response.path("data.key1");
        String key2 = response.path("data.key2");

        // Query Database
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM your_table WHERE id = " + id);
            if (resultSet.next()) {
                String dbKey1 = resultSet.getString("key1");
                String dbKey2 = resultSet.getString("key2");

                // Validate response with database data
                assert key1.equals(dbKey1);
                assert key2.equals(dbKey2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
