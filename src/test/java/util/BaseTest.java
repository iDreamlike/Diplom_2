package util;

import dto.LoginBodyDto;
import dto.UserBodyDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static config.RestAssuredConfig.configRestAssured;
import static data.UserDataGenerator.createUserData;
import static util.UserHelper.*;

public class BaseTest {
    protected UserBodyDto user = new UserBodyDto();
    protected LoginBodyDto login;
    protected String accessToken;

    @BeforeAll
    static void setUpOnce() {
        configRestAssured();
    }

    @AfterEach
    void tearDown() {
        deleteUserIfExists();
    }

    protected void createUser() {
        user = createUserData();
        Response responseFromRegister = registerUser(user);
        accessToken = getAccessToken(responseFromRegister);
    }

    protected void deleteUserIfExists() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }
}
