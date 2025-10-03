package tests;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeAuthTests extends BaseTest {

    @Test
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
    public void noTokenTest() {

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/booking/1")
                .then()
                .statusCode(403);
    }
}