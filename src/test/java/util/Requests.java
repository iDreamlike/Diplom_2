package util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {
    public Requests(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public Response get(String endpoint) {
        return given().contentType(ContentType.JSON).get(endpoint);
    }

    public Response post(String endpoint, Object jsonBody) {
        return given().contentType(ContentType.JSON).body(jsonBody).post(endpoint);
    }
}
