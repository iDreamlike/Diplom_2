package util;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.List;

import static constants.Urls.ORDERS_ENDPOINT;
import static util.HttpRequests.post;
import static util.HttpRequests.get;


public class OrderHelper {

    @Step("Создание заказа с ингредиентами и авторизацией")
    public static Response createOrderWithIngredientsWithAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }

    @Step("Создание заказа с ингредиентами и без авторизации")
    public static Response createOrderWithIngredientsWithoutAuth(List<String> ingredients) {
        return post(ORDERS_ENDPOINT, ingredients);
    }

    @Step("Создание заказа без ингредиентов с авторизацией")
    public static Response createOrderWithoutIngredientsWithAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }

    @Step("Создание заказа без ингредиентов и без авторизации")
    public static Response createOrderWithoutIngredientsWithoutAuth(List<String> ingredients) {
        return post(ORDERS_ENDPOINT, ingredients);
    }

    @Step("Создание заказа с некорректными ингредиентами и с авторизацией")
    public static Response createOrderWithIncorrectIngredientsWithAuth(List<String> ingredients, String accessToken) {
        return post(ORDERS_ENDPOINT, ingredients, accessToken);
    }

    @Step("Создание заказа с некорректными ингредиентами и без авторизации")
    public static Response createOrderWithIncorrectIngredientsWithoutAuth(List<String> ingredients) {
        return post(ORDERS_ENDPOINT, ingredients);
    }

    @Step("Получение заказов с авторизацией")
    public static Response getOrdersWithAuthUser(String accessToken) {
        return get(ORDERS_ENDPOINT, accessToken);
    }

    @Step("Получение заказов без авторизации")
    public static Response getOrdersWithoutAuthUser() {
        return get(ORDERS_ENDPOINT);
    }
}
