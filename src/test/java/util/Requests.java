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

    public Response post(String endpoint, Object jsonBody) {
        return given()
                .body(jsonBody)
                .post(endpoint);
    }

    public Response delete(String endpoint, String accessToken) {
        return given()
        .header("Authorization", accessToken)
        .delete(endpoint);
    }
}
