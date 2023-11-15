package be.switchfully.webapi;

import be.switchfully.user.UserConstantTest;
import be.switchfully.user.domain.Address;
import be.switchfully.user.service.UserService;
import be.switchfully.user.service.dto.CreateUserDTO;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static be.switchfully.user.UserConstantTest.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class UserControllerTest {
    @Inject
    UserService userService;

    @Test
    void getAllUsers() {
        RestAssured.given()
                .auth().preemptive().basic("admin@email.be", "admin")
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .body(not(emptyOrNullString()));
    }

    @Test
    public void testGetAllUsersWithoutAdminCredentials() {
        RestAssured.given()
                .auth().preemptive().basic("laurent@email.be", "user")
                .when()
                .get("/user")
                .then()
                .statusCode(403);
    }

    @Test
    void getUserById() {
        UUID userId = userService.addUser(CREATE_USER_DTO);

        RestAssured.given()
                .auth().preemptive().basic("admin@email.be", "admin")
                .when()
                .get("/user/" + userId)
                .then()
                .log().all()
                .statusCode(200)
                .body(not(emptyOrNullString()));
    }

    @Test
    void getAllOrders() {
        RestAssured.given()
                .auth().preemptive().basic("laurent@email.be", "user")
                .when()
                .get("/user/myOrder")
                .then()
                .statusCode(200)
                .body(not(emptyOrNullString()));
    }

    @Test
    void addUser() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(CREATE_USER_DTO)
                .when()
                .post("/user/register")
                .then()
                .statusCode(201)
                .body(not(emptyOrNullString()));
    }

    @Test
    void addOrder() {
        RestAssured.given()
                .auth().preemptive().basic("laurent@email.be", "user")
                .contentType(ContentType.JSON)
                .body(CREATE_ORDER_DTO)
                .when()
                .post("/user/order")
                .then()
                .statusCode(201)
                .body(not(emptyOrNullString()));
    }
}