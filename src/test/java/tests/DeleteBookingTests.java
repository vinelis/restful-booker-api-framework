package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.Booking;
import pojos.BookingDates;
import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest {

    @Test
    public void deleteBookingTest() {
        Response responseCreate = createBooking();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        String token = getToken();

        given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
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

    private String getToken() {
        String payload = "{\"username\" : \"admin\", \"password\" : \"password123\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/auth");
        return response.jsonPath().getString("token");
    }
}