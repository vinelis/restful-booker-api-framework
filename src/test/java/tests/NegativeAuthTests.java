package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

@Epic("Booking API")
@Feature("Authentication")
public class NegativeAuthTests extends BaseTest {

    @Test
    @Story("Negative Authentication Scenarios")
    @Description("Test attempting to update a resource with an invalid token")
    public void invalidTokenTest() {

        String invalidToken = "invalidToken123";
        String payload = "{\n" +
                "    \"firstname\" : \"NewName\",\n" +
                "    \"lastname\" : \"NewLastName\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + invalidToken)
                .body(payload)
                .when()
                .put("/booking/1")
                .then()
                .statusCode(403);
    }

    @Test
    @Story("Negative Authentication Scenarios")
    @Description("Test attempting to delete a resource with no token")
    public void noTokenTest() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/booking/1")
                .then()
                .statusCode(403);
    }
}