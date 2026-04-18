package resource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class LoginResourceTest {
    
    @Test
    void shouldFailLoginUserDontExist() {
        given()
            .contentType("application/json")
            .body("""
                    {
                        "email": "carlosss@email.com",
                        "password": "passworddd"
                    }
                    """)
            .when()
                .post("auth/login")
                .then()
                    .statusCode(500)
                    .body("message", containsString("User not found"));
    }

    @Test
    void shouldFailLoginInvalidCredentials() {
        given()
            .contentType("application/json")
            .body("""
                    {
                        "email": "carlos@email.com",
                        "password": "passworddd"
                    }
                    """)
            .when()
                .post("auth/login")
                .then()
                    .statusCode(500)
                    .body("message", containsString("Invalid credential"));
    }
}
