package com.ecomPulse.orderservice.util;



public class LogMsg {

    public static class OrderMessages{

        public static String PRODUCT_OUT_OF_STOCK = "Product id: {} and product name: {} has only {} quantity";

        public static String PRODUCT_RESPONSE = "This is product response {}";

        public static String REST_TEMPLATE_EXCEPTION = "Exception Occurred in RestTemplate";

        public static String FIND_ALL_PRODUCT = "Find all product";

        public static String PRODUCT_RETRIVE_ERROR = "Error Occurred while find all products";

        public static String PRODUCT_NOT_FOUND_ERROR = "product not found";

        public static String PRODUCT_RETRIVE_BY_ID_ERROR = "Error Occurred while find product by id";

        public static String PRODUCT_UPDATE_ERROR = "Error Occurred while updating the product: {}";
        public static String PRODUCT_CREATE_ERROR = "Error Occurred while Saving the product into database: {}";


    }



}
