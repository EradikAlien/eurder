package be.switchfully.webapi;

import be.switchfully.item.service.ItemService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static be.switchfully.item.ItemConstantTest.CREATE_ITEM_DTO;
import static be.switchfully.item.ItemConstantTest.UPDATE_ITEM_DTO;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class ItemControllerTest {
    @Inject
    ItemService itemService;

    @Test
    void getAllItems() {
        RestAssured.given()
                .auth().preemptive().basic("admin@email.be", "admin")
                .when()
                .get("/item")
                .then()
                .statusCode(200)
                .body(not(emptyOrNullString()));
    }

    @Test
    void addItem() {
        RestAssured.given()
                .auth().preemptive().basic("admin@email.be", "admin")
                .contentType(ContentType.JSON)
                .body(CREATE_ITEM_DTO)
                .when()
                .post("/item/addItem")
                .then()
                .statusCode(201)
                .body(not(emptyOrNullString()));
    }

    @Test
    void updateItem() {
        Long id = itemService.addItem(CREATE_ITEM_DTO);
                RestAssured.given()
                        .auth().preemptive().basic("admin@email.be", "admin")
                        .contentType(ContentType.JSON)
                        .pathParam("id", id)
                        .body(UPDATE_ITEM_DTO)
                        .when()
                        .put("/item/{id}")
                        .then()
                        .statusCode(201)
                        .body(not(emptyOrNullString()));
    }
}