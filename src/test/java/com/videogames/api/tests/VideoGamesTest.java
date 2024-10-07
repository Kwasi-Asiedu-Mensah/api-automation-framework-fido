package com.videogames.api.tests;

import com.videogames.api.services.AuthService;
import com.videogames.api.services.VideoGamesService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VideoGamesTest {

    private String token;

    @BeforeClass
    public void setup() {
        Response response = AuthService.authenticate("admin", "admin");
        token = response.getBody().jsonPath().getString("token");
    }

    @Test
    public void getAllVideoGames() {
        Response response = VideoGamesService.getAllVideoGames(token);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testCreateVideoGame() {
        Response response = VideoGamesService.createVideoGame(token, "Action", "Call of Duty: Black Ops III", "Mature", "2012-05-04", 85);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getSpecificVideoGame() {
        Response response = VideoGamesService.getSpecificVideoGame(token, 1);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getSpecificVideoGameError() {
        Response response = VideoGamesService.getSpecificVideoGame(token, 123456);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void testUpdateVideoGame() {
        Response response = VideoGamesService.updateVideoGame(token, 2, "Uncharted 4");
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test
    public void testDeleteVideoGame() {
        Response response = VideoGamesService.deleteVideoGame(token, 1);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
