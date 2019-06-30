package com.example.mytemplate.main.view.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.mytemplate.R;
import com.example.mytemplate.main.view.activity.MainActivity;

public class  CustomProgressDialog extends Dialog {

    private static CustomProgressDialog dialog;

    private CustomProgressDialog(Context context) {
        super(context);

        // dialog configuration
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
    }

    public static CustomProgressDialog showDialog(Context context){
        if(context == null) return null;
        if(dialog != null && dialog.isShowing()) return null;

        dialog = new CustomProgressDialog(context);
        dialog.show();

        return dialog;
    }

    public static void closeDialog(){
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }
}
