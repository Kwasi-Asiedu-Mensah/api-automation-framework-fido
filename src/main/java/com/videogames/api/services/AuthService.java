package com.videogames.api.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthService {
    public static Response authenticate(String username, String password) {
        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .post("https://www.videogamedb.uk:443/api/authenticate");

        return response;
    }
}
