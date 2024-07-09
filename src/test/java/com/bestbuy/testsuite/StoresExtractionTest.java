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
public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }


    //1. Extract the limit
    @Test
    public void testNo01() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    //2. Extract the total
    @Test
    public void testNo02() {
        List<Integer> list = response.extract().path("data");
        System.out.println("Total list :" + list);
    }

    //3. Extract the name of 5th store
    @Test
    public void testNo03() {
        String store5th = response.extract().path("data[4].name");
        System.out.println("Store name of 5th is : " + store5th);
    }

    //4. Extract the names of all the store
    @Test
    public void testNo04() {
        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("All Store name are : " + allStoreName);
    }

    //5. Extract the storeId of all the store
    @Test
    public void testNo05() {
        List<Integer> listOfIDs = response.extract().path("data.id");
        System.out.println("List of Store IDs are :" + listOfIDs);
    }

    //6. Print the size of the data list
    @Test
    public void testNo06() {
        List<Integer> data = response.extract().path("data");
        System.out.println("Size of the data is :" + data.size());
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void testNo07() {
        List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("value of store name St Cloud is :" + value);
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void testNo08() {
        String address = response.extract().path("data.find{it.name == 'Rochester'}.address");
        System.out.println("address of the store where store name = Rochester is :" + address);
    }

    //9. Get all the services of 8th store
    @Test
    public void testNo09() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");
        System.out.println("The services list 8th are :" + services.size());
    }

    //10. Get store services of the store where service name = Windows Store
    @Test
    public void testNo10() {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name = 'Windows Store'}.services");
        System.out.println("services of the store where service name = Windows Store are :" + services.size());
        System.out.println("services of the store where service name = Windows Store are :" + services);
    }

    //11. Get all the storeId of all the store
    @Test
    public void testNo11() {
        List<Hashtable<String, ?>> storeId = response.extract().path("data.id");
        System.out.println("storeId of all the store are :" + storeId);
        System.out.println("storeId of all the store size are :" + storeId.size());
    }

    //12. Get id of all the store
    @Test
    public void testNo12() {
        List<Hashtable<String, ?>> storeId = response.extract().path("data.id");
        System.out.println("storeId of all the store are :" + storeId);
        System.out.println("storeId of all the store size are :" + storeId.size());
    }

    //13. Find the store names Where state = ND
    @Test
    public void testNo13() {
        List<String> state = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("Store names Where state = ND is :" + state);
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void testNo14() {
        int categories = response.extract().path("data.find{it.name == 'Rochester'}.services.size()");
        System.out.println("List of services for the store name is Rochester  :" + categories);
    }

    //15. Find the createdAt for all services whose name = “Windows Store”  --->data[2].services[8]
    @Test
    public void testNo15() {
        List<HashMap<String, ?>> serviceName = response.extract().path("data.findAll{it.name}.services");
        System.out.println("List of all services whose store name is “Windows Store” are :" + serviceName);
    }

    //16. Find the name of all services Where store name = “Fargo”
    //17. Find the zip of all the store
    @Test
    public void testNo17() {
        List<String> zip = response.extract().path("data.findAll{it.id}.zip");
        System.out.println("List of zip of all the store are :" + zip);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void testNo18() {
        String zip = response.extract().path("data[2].zip");
        System.out.println("Roseville store zip is :" + zip);
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void testNo19() {
        List<HashMap<String, ?>> storeServDetail = response.extract().path("data.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("storeservices details of the service name is Magnolia Home Theater is :" + storeServDetail);
    }
    //20. Find the lat of all the stores
    @Test
    public void testNo20() {
        List<Integer> lat = response.extract().path("data.findAll{it.id}.lat");
        System.out.println("list of lat " + lat);
    }
}
