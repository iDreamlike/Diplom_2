import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static constants.Messages.ERROR_AUTHORIZATION_MESSAGE;
import static data.OrderDataGenerator.getIngredients;
import static util.OrderHelper.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Получение заказов конкретного пользователя")
public class OrdersUserGetTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        createUser();
        createOrderWithIngredientsWithAuth(
                getIngredients(),
                accessToken);
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя. Пользователь авторизован")
    public void getOrdersWithAuthUserTest() {
        Response response = getOrdersWithAuthUser(accessToken);

        response.then()
                .statusCode(200)
                .body("success", is(true))
                .body("orders", notNullValue())
                .body("total", notNullValue())
                .body("totalToday", notNullValue());
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя. Пользователь не авторизован")
    public void getOrdersWithoutAuthUserTest() {
        Response response = getOrdersWithoutAuthUser();

        response.then()
                .statusCode(401)
                .body("success", is(false))
                .body("message", equalTo(ERROR_AUTHORIZATION_MESSAGE));
    }
}
