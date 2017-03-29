package com.tinhngo.sclassandroid.Common;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinhngo.sclassandroid.Model.ResponseModel;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jundat95 on 21/02/2017.
 */

public class TiSharedPreferences {

    static final String MY_SHARED = "MY_SHARED";

    // Save local String
    public static void saveSharedPreferences(Context context, String tag, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tag,value);
        editor.commit();
    }

    public static String  getSharedPreferences(Context context, String tag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(tag,"");
        if(value.equalsIgnoreCase(""))
            return null;
        return value;
    }

    public static void removeSharedPreferences(Context context, String tag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(tag).commit();
    }

    // Save list ResponseModel in local
    public static void saveListNode(Context context, String tag, List<ResponseModel> listNode){

        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Convert list to json
        Gson gson = new Gson();
        Type type = new TypeToken<List<ResponseModel>>(){}.getType();
        String json = gson.toJson(listNode,type);
        // Put json
        editor.putString(tag,json);
        editor.commit();
    }

    public static List<ResponseModel> getListNode(Context context, String tag){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED,Context.MODE_PRIVATE);
        String value = sharedPreferences.getString(tag,"");
        if(value.equalsIgnoreCase(""))
            return null;

        // Json to list Node
        Gson gson = new Gson();
        Type type = new TypeToken<List<ResponseModel>>(){}.getType();
        List<ResponseModel> listNode = gson.fromJson(value,type);
        return listNode;
    }

}
