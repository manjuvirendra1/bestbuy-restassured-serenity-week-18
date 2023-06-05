package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

//@RunWith(SerenityRunner.class)
public class ProductCrudTestWithSteps extends TestBase {

    static String name = "Amazone5" + TestUtils.getRandomValue();
    static String type = "Battery";
    static double price = 5.99;
    static String upc = "041333424043";
    static double shipping = 1.99;
    static String description = "Amazone Basic Home Battery1" + TestUtils.getRandomValue();
    static String manufacturer = "Amazon China";
    static String model = "Amazon Basic 1.0" + TestUtils.getRandomValue();
    static String url = "http://www.amazon.co.uk/battery";
    static String image = "http://www.amazon.co.uk/battery/imag";
    static int id;


    @Steps
    ProductSteps productSteps;

    @Test
    public void test001() {
        productSteps.getAllProducts();
    }


    @Test
    public void test002() {
        ValidatableResponse response = productSteps.createProduct(id, name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");
    }

    @Test
    public void test003() {
        ValidatableResponse response = productSteps.getSingleProduct(id).statusCode(200);
        response.log().all().body("id", equalTo(id));
    }

    @Test
    public void test004() {
        name = "Duracell Plus - AAA Batteries (12-Pack)";
        ValidatableResponse response = productSteps.updateAProduct(id, name, type, price, upc, shipping, description, manufacturer, model, url, image);
        response.log().all().body("name", equalTo(name));
    }

    @Test
    public void test005() {
        productSteps.deleteAProduct(id);
        productSteps.getSingleProduct(id).statusCode(404);
    }
}
