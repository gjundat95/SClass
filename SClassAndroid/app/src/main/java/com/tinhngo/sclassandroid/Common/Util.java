package com.tinhngo.sclassandroid.Common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;

/**
 * Created by ittin on 11/04/2017.
 */

public class Util {

    public static void initActionBar(AppCompatActivity context){
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
