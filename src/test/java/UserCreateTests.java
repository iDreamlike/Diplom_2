import dto.UserBodyDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Requests;
import net.datafaker.Faker;

import static config.RestAssuredConfig.configRestAssured;
import static constants.Urls.APP_URL;
import static constants.Urls.REGISTER_ENDPOINT;

public class UserCreateTests {
    private Response response;
    private Requests requests;
    private UserBodyDto userJsonBody = new UserBodyDto();
    private final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        requests = new Requests(APP_URL);
        configRestAssured();
        userJsonBody = userJsonBody.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
    }

    @Test
    void userCreateTest() {
        response = requests.post(REGISTER_ENDPOINT, userJsonBody);
        response.then().assertThat().statusCode(200);
    }
}
