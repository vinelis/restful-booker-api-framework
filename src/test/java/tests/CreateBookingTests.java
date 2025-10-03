package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.Booking;
import pojos.BookingDates;
import static io.restassured.RestAssured.given;

@Epic("Booking API")
@Feature("Create Operations")
public class CreateBookingTests extends BaseTest {

    @Test
    @Story("Create a new booking")
    @Description("Test to create a new booking with dynamic data")
    public void createBookingTest() {
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

        Response response = given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");

        response.then()
                .statusCode(200);
    }
}