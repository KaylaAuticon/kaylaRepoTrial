package org.brit.tests;

import org.brit.tests.classes.StatusEnum;
import org.testng.annotations.Test;

import java.util.List;



import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;
import static org.brit.tests.Constants.BASE_URL;
import static org.brit.tests.actions.pets.PetsActions.PET_ENDPOINT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;



public class KaylaTests {
    @Test
    public void getTest() {

        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .queryParam("status", StatusEnum.available.toString())
                .when()
                .get(PET_ENDPOINT + "/findByStatus")
                .then()
                .extract()
                .body()
                .jsonPath()
                .prettyPrint();
    }

    @Test
    public void postTest() {
        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .pathParam("petId", "499278344")
                .get(PET_ENDPOINT + "/{petId}")
                .getBody()
                .prettyPrint();
    }

    @Test
    public void putTest() {
        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .pathParam("petId", "499278344")
                .get(PET_ENDPOINT + "/{petId}")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("Fido6"),
                        "id", is(499278344),
                        "status", equalTo("available"));
    }

    @Test
    public void deleteTest() {





        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 499278344,\n" +
                        "  \"name\": \"Fido6\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .put(PET_ENDPOINT);

        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .pathParam("petId", "499278344")
                .get(PET_ENDPOINT + "/{petId}")
                .then()
                .body("name", equalTo("Fido6"))
                .extract().body().jsonPath()
                .prettyPrint();
    }


}
