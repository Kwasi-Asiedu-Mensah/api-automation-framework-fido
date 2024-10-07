package com.videogames.api.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VideoGamesV2Service {
    public static Response getAllVideoGames(String token) {
        return RestAssured
                .given()
                .auth()
                .oauth2(token)
                .get("https://www.videogamedb.uk/api/v2/videogame");
    }

    public static Response createVideoGame(String token, String category, String name, String rating, String releaseDate, int reviewScore) {
        return RestAssured
                .given()
                .contentType("application/json")
                .body("{\"category\": \"" + category + "\", \"name\": \"" + name + "\", \"rating\": \"" + rating + "\", \"releaseDate\": \"" + releaseDate + "\", \"reviewScore\": " + reviewScore + "}")
                .post("https://www.videogamedb.uk/api/v2/videogame");
    }

    public static Response getVideoGameById(String token, int id) {
        return RestAssured
                .given()
                .get("https://www.videogamedb.uk/api/v2/videogame/" + id);
    }

    public static Response updateVideoGame(String token, int id, String name) {
        // Construct the request body matching the expected schema
        String requestBody = "{\n" +
                "  \"category\": \"Adventure\",\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"rating\": \"E\",\n" +
                "  \"releaseDate\": \"2024-01-01\",\n" +
                "  \"reviewScore\": 95\n" +
                "}";

        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .put("https://www.videogamedb.uk/api/v2/videogame/" + id);
    }


    public static Response deleteVideoGame(String token, int id) {
        return RestAssured
                .given()
                .auth()
                .oauth2(token)
                .delete("https://www.videogamedb.uk/api/v2/videogame/" + id);
    }
}
