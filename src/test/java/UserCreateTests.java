import dto.UserBodyDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Requests;

import static config.RestAssuredConfig.configRestAssured;

public class UserCreateTests {
    private Response response;
    private Requests requests;
    private UserBodyDto userJsonBody = new UserBodyDto();

    @BeforeEach
    void setUp() {
        requests = new Requests("https://stellarburgers.education-services.ru");
        configRestAssured();
        userJsonBody = userJsonBody.toBuilder()
                .email("RandomUser9993@mail.ru")
                .password("1234")
                .name("Вася")
                .build();
    }

    @Test
    void userCreateTest() {
        response = requests.post("api/auth/register", userJsonBody);
        response.then().assertThat().statusCode(200);
    }
}
