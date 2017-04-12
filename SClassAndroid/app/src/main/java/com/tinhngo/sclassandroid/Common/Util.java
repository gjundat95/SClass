package com.tinhngo.sclassandroid.Common;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ittin on 11/04/2017.
 */

public class Util {

    public static void initActionBar(AppCompatActivity context){
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
