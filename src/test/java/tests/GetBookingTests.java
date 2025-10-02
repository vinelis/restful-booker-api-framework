package tests;

import base.BaseTest;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class GetBookingTests extends BaseTest {

    @Test
    public void getBookingIdsTest() {

        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getBookingByIdTest() {

        given()
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .log().body();
    }
}