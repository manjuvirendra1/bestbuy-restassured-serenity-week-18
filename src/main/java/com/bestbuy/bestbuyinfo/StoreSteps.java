package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoint;
import com.bestbuy.constants.Path;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoreSteps {

    @Step("Getting all store details")
    public void getAllStores() {
        SerenityRest.given().log().all()
                .header("Accept", "application/json")
                .when()
                .get(Path.STORES)
                .then().log().all();
    }

    @Step("Creating product with name: {0}, type : {1}, address : {2} , address2 : {3} , city :{4} ,state : {5},zip :{6} ,lat : {7}, lng : {8}and hours : {9}")
    public ValidatableResponse createNewStore(String name, String type, String address, String address2, String city, String state, String zip,
                                              int lat, int lng, String hours) {
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

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(storePojo)
                .when()
                .post(Path.STORES)
                .then();
    }

    @Step("Getting store with id; {0}")
    public ValidatableResponse getAStoreByitId(int id) {

        return SerenityRest.given().log().all()
                .pathParam("storeID", id)
                .when()
                .get(EndPoint.GET_SINGLE_STORE_BY_ID)
                .then();

    }

    @Step("Updating product with storeId: {0}, name: {1}, type : {2}, address : {3} , address2 : {4} , city :{5} ,state : {6},zip :{7} ,lat : {8}, lng : {9}and hours : {10}")
    public ValidatableResponse updateStoreDetail(int storeId, String name, String type, String address, String address2, String city, String state, String zip,
                                                 int lat, int lng, String hours) {
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

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeId)
                .body(storePojo)
                .when()
                .patch(EndPoint.UPDATE_STORE_BY_ID)
                .then();

    }

    @Step("Deleting store by with : {0}")
    public ValidatableResponse deleteStoreById(int storeId) {

        return SerenityRest.given().log().all()
                .pathParam("storeID", storeId)
                .when()
                .delete(EndPoint.DELETE_STORE_BY_ID)
                .then();

    }
}
