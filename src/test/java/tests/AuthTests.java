package tests;

import base.BaseTest;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Booking API")
@Feature("Authentication")
public class AuthTests extends BaseTest {

    @Test
    @Story("Create Token")
    @Description("Test to create an authentication token")
    public void createTokenTest() {
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/auth");

        response.then()
                .statusCode(200)
                .body("token", not(emptyOrNullString()));

        String token = response.jsonPath().getString("token");
    }
}