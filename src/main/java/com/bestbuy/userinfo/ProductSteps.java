package com.bestbuy.userinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class ProductSteps {

    @Step("Creating new products")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc,
                                             String description, String manufacturer, String model, String url,
                                             String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productPojo)
                .when()
                .post(Path.PRODUCTS)
                .then().log().all();
    }

    @Step("Getting products details")
    public ValidatableResponse getProductById(int productID) {
        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("productID", productID)
                .when()
                .get(Path.PRODUCTS + EndPoints.GET_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Updating products details")
    public ValidatableResponse updateProductById(int productID, String name, String type) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("productID", productID)
                .body(productPojo)
                .when()
                .patch(Path.PRODUCTS + EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting products details")
    public ValidatableResponse deleteProductById(int productID) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .delete(Path.PRODUCTS + EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all();
    }
}
