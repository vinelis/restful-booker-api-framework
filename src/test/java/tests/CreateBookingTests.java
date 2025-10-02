package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.Booking;
import pojos.BookingDates;
import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest {

    @Test
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