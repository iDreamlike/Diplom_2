import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.BaseTest;
import net.datafaker.Faker;

import static config.RestAssuredConfig.configRestAssured;
import static constants.Messages.ERROR_TOKEN_MESSAGE;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.UserHelper.*;

public class UserCreateTests extends BaseTest {
    private Response response;
    private final Faker faker = new Faker();

    @BeforeAll
    static void setUpOnce() {
        configRestAssured();
    }

    @AfterEach
    void tearDown() {
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }

    @Test
    void createUniqueUserTest() {
        user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
        response = registerUser(user);

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
}
