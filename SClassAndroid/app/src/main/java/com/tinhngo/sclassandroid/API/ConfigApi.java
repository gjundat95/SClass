package com.tinhngo.sclassandroid.API;

/**
 * Created by tinhngo on 3/25/17.
 */

public class ConfigApi {

    public static final String BASE_URL = "http://45.55.77.182:8888/";
    public static final String LOCALHOST = "http://192.168.200.102/sclass/public/";
    public static final String SPRING = "http://192.168.200.102:8080/";
    public static final String LAB = "http://192.168.137.17:8080/";

    public static String getBaseUrl(){
        return LAB;
    }

}
