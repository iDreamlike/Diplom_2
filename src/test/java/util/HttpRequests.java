package util;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HttpRequests {

    public Response get(String endpoint) {
        return given()
                .get(endpoint);
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
}
