package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
/**
 * Created by Jigar Patel
 */
public class StoresCURDTest extends TestBase {

    static String name = "Mike" + TestUtils.getRandomValue();
    static String type = "sendBox" + TestUtils.getRandomValue();
    static String address = "391 City Road" + TestUtils.getRandomValue();
    static String address2 = "Angel" + TestUtils.getRandomValue();
    static String city = "Islington";
    static String state = "London";
    static String zip = "387001";
    static int storeId;

    @Test
   public void testNo01(){

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        storeId = response.then().contentType(ContentType.JSON).extract().path("id");
        response.then().statusCode(201);
        System.out.println("Id number is" + storeId);
        response.prettyPrint();
    }

    @Test
    public void testNo02() {
        Response response = given()
                .when()
                .get("/stores" + "/" + storeId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void testNo03() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState("USA");
        storePojo.setZip(zip);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/stores" + "/" + storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void testNo04() {
        Response response = given()
                .when()
                .delete("/stores" + "/" + storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
}