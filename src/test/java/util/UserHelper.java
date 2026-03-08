package util;

import dto.LoginBodyDto;
import dto.UserBodyDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static constants.Urls.*;
import static util.HttpRequests.*;

public class UserHelper {

    @Step("Получение токена")
    public static String getAccessToken(Response response) {
        return response.path("accessToken");
    }

    @Step("Регистрация пользователя")
    public static Response registerUser(UserBodyDto user) {
        return post(REGISTER_ENDPOINT, user);
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        delete(USER_ENDPOINT, accessToken);
    }

    @Step("Логин пользователя")
    public static Response loginUser(LoginBodyDto login) {
        return post(LOGIN_ENDPOINT, login);
    }

    @Step("Обновление пользователя с авторизацией")
    public static Response updateUserWithAuthorization(UserBodyDto user, String accessToken) {
        return patch(USER_ENDPOINT, user, accessToken);
    }

    @Step("Обновление пользователя без авторизации")
    public static Response updateUserWithoutAuthorization(UserBodyDto user) {
        return patch(USER_ENDPOINT, user);
    }
}
