import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static constants.Messages.ERROR_AUTHORIZATION_MESSAGE;
import static data.UserDataGenerator.*;
import static org.hamcrest.Matchers.*;
import static util.UserHelper.*;

@DisplayName("Изменение данных пользователя")
public class UserUpdateTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        user = createUserData();
        Response responseFromRegister = registerUser(user);
        accessToken = getAccessToken(responseFromRegister);
    }

    @Test
    @DisplayName("Изменение поля email. Пользователь авторизован")
    void userUpdateEmailAuthorizedTest() {
        updateUserEmailData(user);
        Response responseFromUpdate = updateUserWithAuthorization(user, accessToken);

        responseFromUpdate.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()));
    }

    @Test
    @DisplayName("Изменение поля password. Пользователь авторизован")
    void userUpdatePasswordAuthorizedTest() {
        updateUserPasswordData(user);
        Response responseFromUpdate = updateUserWithAuthorization(user, accessToken);

        responseFromUpdate.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()));
    }

    @Test
    @DisplayName("Изменение поля name. Пользователь авторизован")
    void userUpdateNameAuthorizedTest() {
        updateUserNameData(user);
        Response responseFromUpdate = updateUserWithAuthorization(user, accessToken);

        responseFromUpdate.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()));
    }

    @Test
    @DisplayName("Изменение поля email. Пользователь не авторизован")
    void userUpdateEmailUnauthorizedTest() {
        updateUserEmailData(user);
        Response responseFromUpdate = updateUserWithoutAuthorization(user);

        responseFromUpdate.then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo(ERROR_AUTHORIZATION_MESSAGE));
    }

    @Test
    @DisplayName("Изменение поля password. Пользователь не авторизован")
    void userUpdatePasswordUnauthorizedTest() {
        updateUserPasswordData(user);
        Response responseFromUpdate = updateUserWithoutAuthorization(user);

        responseFromUpdate.then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo(ERROR_AUTHORIZATION_MESSAGE));
    }

    @Test
    @DisplayName("Изменение поля name. Пользователь не авторизован")
    void userUpdateNameUnauthorizedTest() {
        updateUserNameData(user);
        Response responseFromUpdate = updateUserWithoutAuthorization(user);

        responseFromUpdate.then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo(ERROR_AUTHORIZATION_MESSAGE));
    }
}
