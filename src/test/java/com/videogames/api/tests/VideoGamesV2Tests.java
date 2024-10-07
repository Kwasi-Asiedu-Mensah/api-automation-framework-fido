
package com.videogames.api.tests;

import com.videogames.api.services.AuthService;
import com.videogames.api.services.VideoGamesV2Service;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VideoGamesV2Tests {

    private String token;

    @BeforeClass
    public void setup() {
        Response response = AuthService.authenticate("admin", "admin");
        token = response.getBody().jsonPath().getString("token");    }

    @Test
    public void testGetAllVideoGamesV2() {
        Response response = VideoGamesV2Service.getAllVideoGames(token);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testCreateVideoGameV2() {
        Response response = VideoGamesV2Service.createVideoGame(token, "Action", "Spiderman: Far From Home", "Everyone", "2015-10-10", 95);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testGetVideoGameByIdV2() {
        Response response = VideoGamesV2Service.getVideoGameById(token, 2);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testUpdateVideoGameV2() {
        Response response = VideoGamesV2Service.updateVideoGame(token, 2, "Black Myth Wukong");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testDeleteVideoGameV2() {
        Response response = VideoGamesV2Service.deleteVideoGame(token, 2);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}

