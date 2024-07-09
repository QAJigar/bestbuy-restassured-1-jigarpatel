package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
/**
 * Created by Jigar Patel
 */
public class ProductsCRUDTest extends TestBase {

    static String name = "Panasonic - AA Batteries" + TestUtils.getRandomValue();
    static String type = "HardGoods" + TestUtils.getRandomValue();
    static String manufacturer = "Panasonic";
    static String model = "PN0123B4Z";
    static String upc = "987456321";
    static String description = "50% Extra long life";
    static String url = "http://www.bestbuy.com";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static double price = 4.99;
    static double shipping = 3.49;
    static int id;

    @Test
    public void testNo01(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setDescription(description);
        productPojo.setType(type);
        productPojo.setShipping(shipping);
        productPojo.setImage(image);
        productPojo.setPrice(price);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUpc(upc);
        productPojo.setUrl(url);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post("/products");
        id = response.then().contentType(ContentType.JSON).extract().path("id");

        response.then().statusCode(201);
        System.out.println(" Id No is :" + id);
        response.prettyPrint();
    }

    @Test
    public void testNo02(){
        Response response = given()
                .when()
                .get("/products" + "/" + id);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void testNo03(){

        ProductPojo productpojo = new ProductPojo();
        productpojo.setName(name);
        productpojo.setType(type);
        productpojo.setPrice(4.99);
        productpojo.setUpc(upc);
        productpojo.setShipping(shipping);
        productpojo.setDescription(description);
        productpojo.setManufacturer(manufacturer);
        productpojo.setModel(model);
        productpojo.setImage(image);
        productpojo.setUrl(url);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productpojo)
                .patch("/products" + "/" + id);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void testNo04() {
        Response response = given()
                .when()
                .delete("/products" + "/" + id);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
}
