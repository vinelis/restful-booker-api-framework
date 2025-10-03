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
@Feature("Update and Delete Operations")
public class UpdateBookingTests extends BaseTest {

    @Test
    @Story("Update a booking")
    @Description("Test to update an existing booking")
    public void updateBookingTest() {
        Response responseCreate = createBooking();
        int bookingId = responseCreate.jsonPath().getInt("bookingid");

        String token = getToken();

        Faker faker = new Faker();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-01-01");
        bookingDates.setCheckout("2025-01-05");

        Booking updatedBooking = new Booking();
        updatedBooking.setFirstname(faker.name().firstName());
        updatedBooking.setLastname("UpdatedLastName");
        updatedBooking.setTotalprice(faker.number().numberBetween(100, 5000));
        updatedBooking.setDepositpaid(true);
        updatedBooking.setBookingdates(bookingDates);
        updatedBooking.setAdditionalneeds("Dinner");

        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBooking)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
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