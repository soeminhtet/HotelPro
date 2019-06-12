package com.galaxy.hotelpro.Utility;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.galaxy.hotelpro.Activity.LoginActivity;
import com.galaxy.hotelpro.R;
import com.google.android.material.snackbar.Snackbar;

public class Custom_Snackbar {

    public static void getSnackbar(Context context,View v, int textBackground ,int textColor , int img,String message){
        Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View snackView = inflater.inflate(R.layout.custom_snackbar,null);
        ImageView image=snackView.findViewById(R.id.image_snack);
        TextView text=snackView.findViewById(R.id.message_snack);
        text.setBackground(context.getDrawable(textBackground));
        text.setTextColor(context.getResources().getColor(textColor));
        image.setImageResource(img);
        text.setText(message);
        layout.setPadding(0,0,0,0);
        layout.addView(snackView, 0);
        layout.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        snackbar.show();
    }

}
