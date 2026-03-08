package config;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static constants.Urls.APP_URL;

public class RestAssuredConfig {
    public static void configRestAssured() {
        RestAssured.baseURI = APP_URL;
        RestAssured.requestSpecification = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        RestAssured.filters((request, response, ctx) -> {
            System.out.println("\n" + "⏺".repeat(40));
//            System.out.println("📋 Время: " + java.time.LocalTime.now());
            String method = request.getMethod();
            String uri = request.getURI();
            String path = uri.replace(RestAssured.baseURI, "");
            System.out.println("\uD83D\uDD37 " + method + " " + path);
            if (request.getBody() != null) {
                System.out.println("📦 Тело запроса: " + request.getBody().toString() + "\n\n📦 Тело ответа:");
            }
            Response res = ctx.next(request, response);
            System.out.println("📊 Код статуса: " + res.statusCode());
            System.out.println("⏺".repeat(40) + "\n");
            return res;
        });
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.BODY));
    }
}
