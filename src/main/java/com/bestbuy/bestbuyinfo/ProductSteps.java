package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoint;
import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {

    @Step("Getting all products details")
    public ValidatableResponse getAllProducts() {
        return SerenityRest.given().log().all()
                .when()
                .get(Path.Products)
                .then().log().all().statusCode(200);
    }

    @Step("Creating product with name: {0}, type : {1}, price : {2} , shipping : {3} , upc :{4} ,description : {5},manufacturer :{6} ,model : {7}, url : {8}and image : {9}")
    public ValidatableResponse createProduct(int id, String name, String type, double price, String upc, double shipping,
                                              String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(Path.Products)
                .then().log().all();

    }

    @Step("Getting product with id : {0}")
    public ValidatableResponse getSingleProduct(int id) {
        return SerenityRest.given().log().all()
                .pathParam("productID", id)
                .when()
                .get(EndPoint.GET_SINGLE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Updating existing  product with id : {0}, name: {1}, type : {2}, price : {3} , shipping : {4} , upc :{5} ,discription : {6},manufacturer :{7} ,model : {8}, url : {8}and image : {9}")
    public ValidatableResponse updateAProduct(int id, String name, String type, double price, String upc, double shipping,
                                              String description, String manufacturer, String model, String url, String image) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("productID", id)
                .body(productPojo)
                .when()
                .put(EndPoint.UPDATE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);


    }


    @Step("Deleting existing product with id : {0}")
    public ValidatableResponse deleteAProduct(int id) {
        return SerenityRest.given().log().all()
                .pathParam("productID", id)
                .when()
                .delete(EndPoint.DELETE_PRODUCT_BY_ID)
                .then().log().all().statusCode(200);
    }
}
