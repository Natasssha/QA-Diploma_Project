package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class Api {

    private static final String uri = System.getProperty("sut.url");
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(uri)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static String buyForm (Data.CardData cardData) {
        return given()
                .spec(requestSpecification)
                .body(cardData)
                .when()
                .post("/api/v1/pay")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response().asString();
    }

    public static String creditForm (Data.CardData cardData) {
        return given()
                .spec(requestSpecification)
                .body(cardData)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200)
                .extract().response().asString();
    }
}
