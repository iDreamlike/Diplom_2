import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static data.OrderDataGenerator.getIngredients;
import static data.UserDataGenerator.createUserData;
import static util.OrderHelper.createOrderWithIngredientsAuth;
import static org.hamcrest.Matchers.*;
import static util.UserHelper.getAccessToken;
import static util.UserHelper.registerUser;

@DisplayName("Создание заказа")
public class OrderCreateTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        user = createUserData();
        Response responseFromRegister = registerUser(user);
        accessToken = getAccessToken(responseFromRegister);
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами. Пользователь авторизован")
    public void createOrderWithIngredientsAuthTest() {
        Response response = createOrderWithIngredientsAuth(
                getIngredients(),
                accessToken);

        response.then()
                .statusCode(200)
                .body("success", is(true))
                .body("name", notNullValue())
                .body("order.number", notNullValue());
    }
}
