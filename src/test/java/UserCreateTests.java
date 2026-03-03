import dto.UserBodyDto;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Requests;

public class UserCreateTests {
    private Response response;
    private Requests requests;
    private UserBodyDto userJsonBody = new UserBodyDto();

    @BeforeEach
    void setUp() {
        requests = new Requests("https://stellarburgers.education-services.ru");
        userJsonBody = userJsonBody.toBuilder()
                .email("RandomUser9992@mail.ru")
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
