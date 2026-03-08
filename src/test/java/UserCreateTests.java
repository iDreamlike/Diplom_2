import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static constants.Messages.*;
import static data.UserCreateData.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.UserHelper.*;

@DisplayName("Создание пользователя")
public class UserCreateTests extends BaseTest {

    @AfterEach
    void tearDown() {
            deleteUserIfExists();
    }

    @Test
    @DisplayName("Создание уникального пользователя")
    void createUniqueUserTest() {
        user = createUser();
        Response response = registerUser(user);

        response.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());

        accessToken = getAccessToken(response);
        assertNotNull(accessToken, ERROR_TOKEN_MESSAGE);
    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    void createExistsUserTest() {
        user = createUser();
        Response firstResponse = registerUser(user);
        accessToken = getAccessToken(firstResponse);
        Response secondResponse = registerUser(user);

        secondResponse.then()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo(ERROR_REGISTER_EXISTS_USER_MESSAGE));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного поля email")
    void createUserWithoutEmailTest() {
        user = createUserWithoutEmail();
        Response response = registerUser(user);
        response.then()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo(ERROR_REGISTER_WITHOUT_REQUIRED_FIELDS_MESSAGE));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного поля password")
    void createUserWithoutPasswordTest() {
        user = createUserWithoutPassword();
        Response response = registerUser(user);
        response.then()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo(ERROR_REGISTER_WITHOUT_REQUIRED_FIELDS_MESSAGE));
    }

    @Test
    @DisplayName("Создание пользователя без обязательного поля name")
    void createUserWithoutNameTest() {
        user = createUserWithoutName();
        Response response = registerUser(user);
        response.then()
                .statusCode(403)
                .body("success", is(false))
                .body("message", equalTo(ERROR_REGISTER_WITHOUT_REQUIRED_FIELDS_MESSAGE));
    }
}