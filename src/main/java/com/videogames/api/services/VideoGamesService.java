package com.videogames.api.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VideoGamesService {
    public static Response getAllVideoGames(String token) {
        return RestAssured
                .given()
                .auth()
                .oauth2(token)
                .get("https://www.videogamedb.uk/api/videogame");
    }

    public static Response createVideoGame(String token, String category, String name, String rating, String releaseDate, int reviewScore) {

        var response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"category\": \"" + category + "\", \"name\": \"" + name + "\", \"rating\": \"" + rating + "\", \"releaseDate\": \"" + releaseDate + "\", \"reviewScore\": " + reviewScore + "}")
                .post("https://www.videogamedb.uk/api/videogame");
        return response;
    }

    public static Response getSpecificVideoGame(String token, int id) {
        var response = RestAssured
                .given()
                .get("https://www.videogamedb.uk/api/videogame/" + id);
        return response;
    }

    public static Response updateVideoGame(String token, int id, String name) {
        String requestBody = "{\n" +
                "  \"category\": \"Adventure\",\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"rating\": \"E\",\n" +
                "  \"releaseDate\": \"2024-01-01\",\n" +
                "  \"reviewScore\": 95\n" +
                "}";

        var  response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .put("https://www.videogamedb.uk/api/videogame/" + id);
        return response;
    }


    public static Response deleteVideoGame(String token, int id) {
        return RestAssured
                .given()
                .auth()
                .oauth2(token)
                .delete("https://www.videogamedb.uk/api/videogame/" + id);
    }
}
