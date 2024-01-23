package com.bestbuy.userinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoreCRUDTest extends TestBase {

    static String name = "Prime" + TestUtils.getRandomValue() + "123";
    static String type = "Testing";
    static String address = "Villein";
    static String address2 = "Lake";
    static String city = "MK";
    static String state = "UK";
    static String zip = "MK107LN";
    static int lat = 123456;
    static int lng = 456789;
    static String hours = "2";

    static int storeID;

    @Steps
    StoreSteps steps;

    @Title("This will create new store")
    @Test
    public void test001() {
        HashMap<String, Object> services = new HashMap<>();
        services.put("JAVA", "3 Weeks");
        services.put("API", "2 Weeks");

        ValidatableResponse response = steps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.statusCode(201);
        storeID = response.extract().path("id");
        System.out.println("ID = " + storeID);
    }

    @Title("This will get the store details")
    @Test
    public void test002() {
        ValidatableResponse response = steps.getStoreById(storeID);
        response.statusCode(200);
    }

    @Title("This will update the store details")
    @Test
    public void test003() {
        ValidatableResponse response = steps.updateStoreById(storeID, name, type);
        response.statusCode(200);
    }

    @Title("This will delete the store details")
    @Test
    public void test004() {
        ValidatableResponse response = steps.deleteStoreById(storeID);
        response.statusCode(200);

        ValidatableResponse response1 = steps.getStoreById(storeID);
        response1.statusCode(404);
    }
}
