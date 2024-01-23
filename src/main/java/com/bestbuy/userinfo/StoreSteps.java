package com.bestbuy.userinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

public class StoreSteps {
    @Step("Creating new store")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city,
                                           String state, String zip, int lat, int lng, String hours,
                                           HashMap<String, Object> services) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(storePojo)
                .post(Path.STORES)
                .then().log().all();
    }

    @Step("Getting store details")
    public ValidatableResponse getStoreById(int storeID) {
        return SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .pathParam("storeID", storeID)
                .when()
                .get(Path.STORES + EndPoints.GET_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Updating store details")
    public ValidatableResponse updateStoreById(int storeID, String name, String type) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name + "_123");
        productPojo.setType(type + "_good");

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("storeID", storeID)
                .body(productPojo)
                .when()
                .patch(Path.STORES + EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Deleting store details")
    public ValidatableResponse deleteStoreById(int storeID) {
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .delete(Path.STORES + EndPoints.DELETE_STORE_BY_ID)
                .then().log().all();
    }
}
