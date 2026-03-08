import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static data.UserCreateData.*;
import static org.hamcrest.Matchers.*;
import static util.UserHelper.*;

@DisplayName("Изменение данных пользователя")
public class UserUpdateTests extends BaseTest {

    @Test
    @DisplayName("Изменение поля email. Пользователь авторизован")
    void userUpdateEmailAuthorizedTest() {
        user = createUserData();
        Response responseFromRegister = registerUser(user);
        accessToken = getAccessToken(responseFromRegister);
        updateUserEmailData(user);
        Response responseFromUpdate = updateUser(user, accessToken);

        responseFromUpdate.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.email", equalTo(user.getEmail()));
    }

    @Test
    @DisplayName("Изменение поля password. Пользователь авторизован")
    void userUpdatePasswordAuthorizedTest() {
        user = createUserData();
        Response responseFromRegister = registerUser(user);
        accessToken = getAccessToken(responseFromRegister);
        updateUserPasswordData(user);
        Response responseFromUpdate = updateUser(user, accessToken);

        responseFromUpdate.then()
                .statusCode(200)
                .body("success", is(true))
                .body("user.password", equalTo(user.getPassword()));
    }
}
