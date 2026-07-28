package tests;

import base.BaseTest;
import static io.restassured.RestAssured.*;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.Booking;
import pojos.BookingDates;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
        Response responseCreate = createBooking();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/booking-schema.json"))
                .log().body();
    }

    private Response createBooking() {
        Faker faker = new Faker();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-01-01");
        bookingDates.setCheckout("2024-01-05");

        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.number().numberBetween(100, 5000));
        booking.setDepositpaid(faker.bool().bool());
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");

        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
    }
}