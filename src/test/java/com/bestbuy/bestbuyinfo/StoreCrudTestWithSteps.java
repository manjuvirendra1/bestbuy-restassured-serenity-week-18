package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SerenityRunner.class)
public class StoreCrudTestWithSteps extends TestBase {
    static String name = "Minnetonka";
    static String type = "Bigbox";
    static String address = "13513 Ridgedale Dr";
    static String address2 = "";
    static String city = "Hopkins";
    static String state = "MN";
    static String zip = "55305";
    static int lat = 35;
    static int lng = 23;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int id;
    @Steps
    StoreSteps storeSteps;

    @Title("This will get all stores details")
    @Test
    public void test001()
    {
        storeSteps.getAllStores();
    }
    @Title("This method will create new store details")
    @Test
    public void test002() {
        ValidatableResponse response = storeSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");
    }
    @Title("This will get single store details")
    @Test
    public void test003() {
        storeSteps.getAStoreByitId(id).statusCode(200);

    }
    @Title("This will update single store details")
    @Test
    public void test004() {
        name = name + "O2 Arena";
        ValidatableResponse response = storeSteps.updateStoreDetail(id, name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().body("name", equalTo(name));
    }
    @Title("This will delete single store details")
    @Test
    public void test005() {
        storeSteps.deleteStoreById(id).statusCode(200);
        storeSteps.getAStoreByitId(id).statusCode(404);
    }
}
