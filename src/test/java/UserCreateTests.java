import dto.UserBodyDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Requests;
import net.datafaker.Faker;

import static config.RestAssuredConfig.configRestAssured;
import static constants.Urls.REGISTER_ENDPOINT;
import static constants.Urls.USER_ENDPOINT;

public class UserCreateTests {
    private Response response;
    private Requests requests = new Requests();
    private UserBodyDto userJsonBody = new UserBodyDto();
    private final Faker faker = new Faker();
    private String accessToken;

    @BeforeAll
    static void setUpOnce() {
        configRestAssured();
    }

//    @BeforeEach
//    void setUp() {
//        requests = new Requests();
//    }

    @AfterEach
    void tearDown() {
        requests.delete(USER_ENDPOINT, accessToken);
    }

    @Test
    void createUniqueUserTest() {
        userJsonBody = userJsonBody.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
        response = requests.post(REGISTER_ENDPOINT, userJsonBody);
        response.then().assertThat().statusCode(200);
        accessToken = response.getBody().jsonPath().getString("accessToken");
    }
}
