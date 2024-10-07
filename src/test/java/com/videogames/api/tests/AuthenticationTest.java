package com.videogames.api.tests;

import com.videogames.api.services.AuthService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AuthenticationTest {
    @Test
    public void testAuthenticationSuccess() throws IOException {
        // Call the AuthService to authenticate
        Response response = AuthService.authenticate("admin", "admin");
        String token = response.getBody().jsonPath().getString("token");

        // Check that the token is not null
        Assert.assertNotNull(token, "Token should not be null");
        //Assert.assertEquals(response.getStatusCode(), 200);    }
    }

    @Test
    public void testInvalidAuthentication() throws IOException {
        // Call the AuthService to authenticate
        Response response = AuthService.authenticate("user", "user");


        // Check that the token is not null
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}

