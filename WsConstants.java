package com.example.utils;

public class WsConstants {

    public WsConstants() {
    }
    
    public final static String BASE_URL = "http://example.com/";
    public final static int CONNECTION_TIMEOUT = 30;

    /**
     * API name for web service implementation
     */
    public final static String METHOD_LOGIN = "customer_login";
    public final static String METHOD_REGISTRATION = "customer_add";
    public final static String METHOD_FORGOT = "forgot_password";
    
    // Base params
    public final String PARAMS_SETTING = "settings";
    public final String PARAMS_DATA = "data";
    public final String PARAMS_SUCCESS = "success";
    public final String PARAMS_MESSAGE = "message";
    public final String PARAMS_ERROR = "error";
    public static final String PARAMS_TOKEN = "token";
    
    // Params
    public final String PARAMS_FNAME = "first_name";
    public final String PARAMS_LNAME = "last_name";
    public final String PARAMS_USERNAME = "username";
    public final String PARAMS_EMAIL = "email";
    public final String PARAMS_PASSWORD = "password";
    public final String PARAMS_PROFILE_IMAGE = "profile_image";
}
