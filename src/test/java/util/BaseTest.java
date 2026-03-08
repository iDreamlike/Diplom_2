package util;

import dto.LoginBodyDto;
import dto.UserBodyDto;
import org.junit.jupiter.api.BeforeAll;

import static config.RestAssuredConfig.configRestAssured;
import static util.UserHelper.deleteUser;

public class BaseTest {
    protected UserBodyDto user = new UserBodyDto();
    protected LoginBodyDto login;
    protected String accessToken;

    @BeforeAll
    static void setUpOnce() {
        configRestAssured();
    }

    protected void deleteUserIfExists() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }
}
