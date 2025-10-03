package tests;

import base.BaseTest;
import static io.restassured.RestAssured.*;

import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Booking API")
@Feature("Read Operations")
public class GetBookingTests extends BaseTest {


    @Test
    @Story("Get all booking IDs")
    @Description("Test to retrieve all booking IDs from the API")
    public void getBookingIdsTest() {

        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    @Story("Get a specific booking by ID")
    @Description("Test to retrieve details of a specific booking by its ID")
    public void getBookingByIdTest() {

        given()
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .log().body();
    }
}