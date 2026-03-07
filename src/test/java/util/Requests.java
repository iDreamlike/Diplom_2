package util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {

    public Response get(String endpoint) {
        return given()
                .get(endpoint);
    }

    public static Response post(String endpoint, Object user) {
        return given()
                .body(user)
                .post(endpoint);
    }

    public static Response delete(String endpoint, String accessToken) {
        return given()
        .header("Authorization", accessToken)
        .delete(endpoint);
    }
}
