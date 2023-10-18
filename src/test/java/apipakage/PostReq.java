package apipakage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostReq {
    private String baseURL = "https://reqres.in/api/login";

    @BeforeClass
    public void setup() {
        // Set the base URI for RestAssured
        RestAssured.baseURI = baseURL;
    }

    @Test
    public void testPositiveLogin() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
        .when()
            .post()
        .then().log().body()
            .statusCode(200)
            .body("token", not(emptyOrNullString()));
    }

    @Test
    public void testNegativeLogin() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"email\": \"test@gmail.com\", \"password\": \"test\"}")
        .when()
            .post()
        .then().log().body()
            .statusCode(400)
            .body("error", equalTo("user not found"));
    }

}
