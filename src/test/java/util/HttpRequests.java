package util;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HttpRequests {

    public static Response get(String endpoint, String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .get(endpoint);
    }

    public static Response get(String endpoint) {
        return given().get(endpoint);
    }

    public static Response post(String endpoint, Object user) {
        return given()
                .body(user)
                .post(endpoint);
    }

    public static void delete(String endpoint, String accessToken) {
        given()
                .header("Authorization", accessToken)
                .delete(endpoint);
    }

    public static Response patch(String endpoint, Object user, String accessToken) {
        return given()
                .body(user)
                .header("Authorization", accessToken)
                .patch(endpoint);
    }

    public static Response patch(String endpoint, Object user) {
        return given()
                .body(user)
                .patch(endpoint);
    }

    public static Response post(String endpoint, List<String> ingredients, String accessToken) {
        return given()
                .body(Map.of("ingredients", ingredients))
                .header("Authorization", accessToken)
                .post(endpoint);
    }

    public static Response post(String endpoint, List<String> ingredients) {
        return given()
                .body(Map.of("ingredients", ingredients))
                .post(endpoint);
    }
}
