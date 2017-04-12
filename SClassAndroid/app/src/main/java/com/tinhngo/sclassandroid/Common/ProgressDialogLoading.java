package com.tinhngo.sclassandroid.Common;

import android.app.ProgressDialog;
import android.content.Context;

import com.tinhngo.sclassandroid.R;


/**
 * Created by tinhngo on 2/16/17.
 */

public class ProgressDialogLoading {

    private  ProgressDialog progressDialog;
    private  Context context;
    private  String  text;


    public ProgressDialogLoading(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    public  void showProgressDialog(){
        progressDialog = new ProgressDialog(context,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(text);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public  void dismissProgressDialog(){
        progressDialog.dismiss();
    }

}
