package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import static io.restassured.RestAssured.given;
/**
 * Created by Jigar Patel
 */
public class ProductsExtractionTest {

    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }



    //1. Extract the limit
    @Test
    public void testNo01(){
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }
    //2. Extract the total
    @Test
    public void testNo02(){
       List<Integer> list = response.extract().path("data");
        System.out.println("Total list :" + list);
    }
    //3. Extract the name of 5th product
    @Test
    public void testNo03(){
        String product5th = response.extract().path("data[4].name");
        System.out.println("Product name of 5th is : " + product5th);
    }
    //4. Extract the names of all the products
    @Test
    public void testNo04(){
       List<String> allProductName = response.extract().path("data.name");
        System.out.println("All Product name are : " + allProductName);
    }
    //5. Extract the productId of all the products
    @Test
            public void testNo05() {
        List<Integer> listOfIDs = response.extract().path("data.id");
        System.out.println("List of IDs are :" + listOfIDs);
    }

    //6. Print the size of the data list
    @Test
    public void testNo06() {
        List<Integer> data = response.extract().path("data");
        System.out.println("Size of the data is :" + data.size());
    }

    //7. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void testNo07() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("List of IDs are :" + value);
    }

    //8. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void testNo08() {
        List<HashMap<String, ?>> model = response.extract().path("data.findAll{it.model == 'Energizer - N Cell E90 Batteries (2-Pack)'}");
        System.out.println("List of product name = 'Energizer - N Cell E90 Batteries (2-Pack)' are :" + model);
    }

    //9. Get all the categories of 8th products
    @Test
    public void testNo09() {
        List<HashMap<String,?>>  categories = response.extract().path("data[7].categories");
        System.out.println("The Categories list 8th are :" + categories.size());
    }

    //10. Get categories of the store where product id = 150115
    @Test
    public void testNo10() {
        List<HashMap<String,?>> productId = response.extract().path("data.findAll{it.id == '150115'}.categories");
        System.out.println("List categories of the store where product id = 150115 are :" + productId.size());
    }

    //11. Get all the descriptions of all the products
    @Test
    public void testNo11() {
        List<Hashtable<String, ?>> descriptions = response.extract().path("data.description");
        System.out.println("descriptions of all the products are :" + descriptions);
    }

    //12. Get id of all the all categories of all the products
    @Test
    public void testNo12() {
        List<Hashtable<String, ?>> categories = response.extract().path("data.categories");
        System.out.println("all the all categories of all the products are :" + categories);
    }

    //13. Find the product names Where type = HardGood
    @Test
    public void testNo13() {
        List<String>  type = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("product names Where type is :" + type);
    }

    //14. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void testNo14() {
         int categories = response.extract().path("data.find{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories.size()");
        System.out.println("List of categories for the product name  :" + categories);
    }

    //15. Find the created At for all products whose price < 5.49
    @Test
    public void testNo15() {
        List<String> proName = response.extract().path("data.findAll{it.price < 5.49}.name");
        System.out.println("List of products whose price < 5.49 are :" + proName);
    }

    //16. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void testNo16() {
        List<HashMap<String,?>> allCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories");
        System.out.println("List of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)” are :" + allCategories);
    }

    //17. Find the manufacturer of all the products
    @Test
    public void testNo17() {
        List<String> manu = response.extract().path("data.findAll{it.id}.manufacturer");
        System.out.println("List of manufacturer of all the products are :" + manu);
    }

    //18. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void testNo18() {
        List<String> listOfImg = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("List of IDs are :" + listOfImg);
    }

    //19. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void testNo19() {
        List<String> catName = response.extract().path("data.findAll{it.price < 5.49}.categories");
        System.out.println("List of all categories products whose price > 5.99 are :" + catName);
    }

    //20. Find the uri of all the products
    @Test
    public void testNo20() {
        List<String> uri = response.extract().path("data.url");
        System.out.println("List of all url are :" + uri);
        System.out.println("List of all url size are :" + uri.size());
    }
}
