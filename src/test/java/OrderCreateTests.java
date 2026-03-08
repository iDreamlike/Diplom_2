import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.BaseTest;

import static constants.Messages.ERROR_ORDER_INCORRECT_IDS_MESSAGE;
import static constants.Messages.ERROR_ORDER_WITHOUT_IDS_MESSAGE;
import static data.OrderDataGenerator.*;
import static org.hamcrest.Matchers.*;
import static util.OrderHelper.*;

@DisplayName("Создание заказа")
public class OrderCreateTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        createUser();
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами. Пользователь авторизован")
    public void createOrderWithIngredientsWithAuthTest() {
        Response response = createOrderWithIngredientsWithAuth(
                getIngredients(),
                accessToken);

        response.then()
                .statusCode(200)
                .body("success", is(true))
                .body("name", notNullValue())
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа с ингредиентами. Пользователь не авторизован")
    public void createOrderWithIngredientsWithoutAuthTest() {
        Response response = createOrderWithIngredientsWithoutAuth(getIngredients());

        response.then()
                .statusCode(200)
                .body("success", is(true))
                .body("name", notNullValue())
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов. Пользователь авторизован")
    public void createOrderWithoutIngredientsWithAuthTest() {
        Response response = createOrderWithoutIngredientsWithAuth(
                getNoIngredients(),
                accessToken);

        response.then()
                .statusCode(400)
                .body("success", is(false))
                .body("message", equalTo(ERROR_ORDER_WITHOUT_IDS_MESSAGE));
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов. Пользователь не авторизован")
    public void createOrderWithoutIngredientsWithoutAuthTest() {
        Response response = createOrderWithoutIngredientsWithoutAuth(getNoIngredients());

        response.then()
                .statusCode(400)
                .body("success", is(false))
                .body("message", equalTo(ERROR_ORDER_WITHOUT_IDS_MESSAGE));
    }

    @Test
    @DisplayName("Создание заказа c неверным хешем. Пользователь авторизован")
    public void createOrderWithIncorrectIngredientsWithAuthTest() {
        Response response = createOrderWithIncorrectIngredientsWithAuth(
                getIncorrectIngredients(),
                accessToken);

        response.then()
                .statusCode(500)
                .contentType("text/html; charset=utf-8")
                .body(containsString(ERROR_ORDER_INCORRECT_IDS_MESSAGE));
    }

    @Test
    @DisplayName("Создание заказа c неверным хешем. Пользователь не авторизован")
    public void createOrderWithIncorrectIngredientsWithoutAuthTest() {
        Response response = createOrderWithIncorrectIngredientsWithoutAuth(getIncorrectIngredients());

        response.then()
                .statusCode(500)
                .contentType("text/html; charset=utf-8")
                .body(containsString(ERROR_ORDER_INCORRECT_IDS_MESSAGE));
    }
}
