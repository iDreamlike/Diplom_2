package util;

import dto.UserBodyDto;
import io.restassured.response.Response;

import static constants.Urls.REGISTER_ENDPOINT;
import static constants.Urls.USER_ENDPOINT;
import static util.Requests.post;
import static util.Requests.delete;

public class UserHelper {

//    private Requests requests = new Requests();

    public static String getAccessToken(Response response) {
        return response.path("accessToken");
    }

    public static Response registerUser(UserBodyDto user) {
        return post(REGISTER_ENDPOINT, user);
    }

    public static Response deleteUser(String accessToken) {
        return delete(USER_ENDPOINT, accessToken);
    }

}
