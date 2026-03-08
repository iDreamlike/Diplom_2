package util;

import io.restassured.response.Response;

import java.util.List;

import static constants.Urls.ORDERS_ENDPOINT;
import static util.HttpRequests.post;


public class OrderHelper {
    public static Response createOrderWithIngredientsAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }
}
