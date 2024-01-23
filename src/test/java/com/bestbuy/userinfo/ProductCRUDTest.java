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

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCRUDTest extends TestBase {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String type = "Testing";
    static int price = 1850;
    static int shipping = 1;
    static String upc = "JAVA";
    static String description = "API";
    static String manufacturer = "4Pillar";
    static String model = "Testing";
    static String url = "prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String image = "prime.jpg";

    static int productID;

    @Steps
    ProductSteps steps;

    @Title("This will create new product")
    @Test
    public void test001() {
        ValidatableResponse response = steps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.statusCode(201);

        productID = response.extract().path("id");
        System.out.println("ID = " + productID);
    }

    @Title("This will get the product details")
    @Test
    public void test002() {
        ValidatableResponse response= steps.getProductById(productID);
        response.statusCode(200);
    }

    @Title("This will update the product details")
    @Test
    public void test003() {
        ValidatableResponse response = steps.updateProductById(productID, name, type);
        response.statusCode(200);
    }

    @Title("This will delete the product details")
    @Test
    public void test004() {
        ValidatableResponse response = steps.deleteProductById(productID);
        response.statusCode(200);

        ValidatableResponse response1 = steps.getProductById(productID);
        response.statusCode(200);
    }

}
