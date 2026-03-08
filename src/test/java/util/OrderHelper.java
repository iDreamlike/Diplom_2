package util;

import io.restassured.response.Response;

import java.util.List;

import static constants.Urls.ORDERS_ENDPOINT;
import static util.HttpRequests.post;
import static util.HttpRequests.get;


public class OrderHelper {
    public static Response createOrderWithIngredientsWithAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }

    public static Response createOrderWithIngredientsWithoutAuth(List<String> ingredients) {
        return post(ORDERS_ENDPOINT, ingredients);
    }

    public static Response createOrderWithoutIngredientsWithAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }

    public static Response createOrderWithoutIngredientsWithoutAuth(List<String> ingredients) {
        return post(ORDERS_ENDPOINT, ingredients);
    }

    public static Response createOrderWithIncorrectIngredientsWithAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }

    public static Response createOrderWithIncorrectIngredientsWithoutAuth(List<String> ingredients) {
        return post(ORDERS_ENDPOINT, ingredients);
    }

    public static Response getOrdersWithAuthUser(String accessToken) {
        return get(ORDERS_ENDPOINT, accessToken);
    }

    public static Response getOrdersWithoutAuthUser() {
        return get(ORDERS_ENDPOINT);
    }
}
