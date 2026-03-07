package util;

import dto.UserBodyDto;
import io.restassured.response.Response;

import static constants.Urls.REGISTER_ENDPOINT;
import static constants.Urls.USER_ENDPOINT;
import static util.HttpRequests.post;
import static util.HttpRequests.delete;

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

}
