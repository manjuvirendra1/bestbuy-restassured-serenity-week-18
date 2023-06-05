package com.bestbuy.constants;

public class EndPoint {
    public static final String DELETE_PRODUCT_BY_ID= "/products/{productID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productID}";

    public static final String CREATE_STORE = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeID}";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeID}";
}
