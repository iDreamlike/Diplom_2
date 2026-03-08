package util;

import dto.LoginBodyDto;
import dto.UserBodyDto;
import io.restassured.response.Response;

import static constants.Urls.*;
import static util.HttpRequests.*;

public class UserHelper {

    public static String getAccessToken(Response response) {
        return response.path("accessToken");
    }

    public static Response registerUser(UserBodyDto user) {
        return post(REGISTER_ENDPOINT, user);
    }

    public static void deleteUser(String accessToken) {
        delete(USER_ENDPOINT, accessToken);
    }

    public static Response loginUser(LoginBodyDto login) {
        return post(LOGIN_ENDPOINT, login);
    }

    public static Response updateUserWithAuthorization(UserBodyDto user, String accessToken) {
        return patch(USER_ENDPOINT, user, accessToken);
    }

    public static Response updateUserWithoutAuthorization(UserBodyDto user) {
        return patch(USER_ENDPOINT, user);
    }
}
