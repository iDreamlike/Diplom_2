import dto.LoginBodyDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static constants.Messages.ERROR_LOGIN_INCORRECT_MESSAGE;
import static constants.Messages.ERROR_TOKEN_MESSAGE;
import static data.UserCreateData.createUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.UserHelper.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Логин пользователя")
public class UserLoginTests extends BaseTest {

    @AfterEach
    void tearDown() {
        deleteUserIfExists();
    }

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void loginWithExistsUserTest() {
        user = createUser();
        registerUser(user);
        login = new LoginBodyDto(user.getEmail(), user.getPassword());
        Response response = loginUser(login);

        response.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail().toLowerCase()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());

        accessToken = getAccessToken(response);
        assertNotNull(accessToken, ERROR_TOKEN_MESSAGE);
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void loginWithIncorrectPassword() {
        user = createUser();
        Response registerResponse = registerUser(user);
        accessToken = getAccessToken(registerResponse);
        assertNotNull(accessToken, ERROR_TOKEN_MESSAGE);
        login = new LoginBodyDto(user.getEmail(), "Incorrect Password");

        Response loginResponse = loginUser(login);

        loginResponse.then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo(ERROR_LOGIN_INCORRECT_MESSAGE));

        accessToken = getAccessToken(registerResponse);
        assertNotNull(accessToken, ERROR_TOKEN_MESSAGE);
    }
}
