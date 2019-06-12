package com.galaxy.hotelpro.Utility;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.galaxy.hotelpro.Activity.AvailableRoomsActivity;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.R;

public class FullScreen {

    public static void getFullScreen(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void getToolbar(AppCompatActivity activity, int id,String title){
        Toolbar toolbar = (Toolbar)activity.findViewById(id);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setTitle(title);
    }
}
