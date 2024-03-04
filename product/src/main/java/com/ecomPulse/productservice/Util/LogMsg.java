package com.ecomPulse.productservice.Util;

import org.springframework.stereotype.Component;


public class LogMsg {

    public static class ProductMessages{

        public static String PRODUCT_CREATE = "Save the product into database: {}";

        public static String PRODUCT_BY_ID = "Find product by id : {}";
        public static String PRODUCT_UPDATED = "Product updated : {}";

        public static String FIND_ALL_PRODUCT = "Find all product";

        public static String PRODUCT_RETRIVE_ERROR = "Error Occurred while find all products";

        public static String PRODUCT_NOT_FOUND_ERROR = "product found";

        public static String PRODUCT_RETRIVE_BY_ID_ERROR = "Error Occurred while find product by id";

        public static String PRODUCT_UPDATE_ERROR = "Error Occurred while updating the product: {}";
        public static String PRODUCT_CREATE_ERROR = "Error Occurred while Saving the product into database: {}";


    }



}
