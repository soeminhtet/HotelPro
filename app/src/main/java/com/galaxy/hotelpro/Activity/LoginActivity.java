package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.R;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        final ImageView lSetting=(ImageView) findViewById(R.id.lSetting);
        final ImageView lWifi=(ImageView)findViewById(R.id.lWifi);
        //final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.loginLinear);
        MaterialButton lExit=(MaterialButton) findViewById(R.id.lExitBtn);
        MaterialButton loginBtn=(MaterialButton) findViewById(R.id.loginBtn);


        lSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float deg = lSetting.getRotation() + 360F;
                lSetting.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setView(R.layout.login_setting_dialog);
                builder.setTitle("Title");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setLayout(700, 850);
            }
        });

        lWifi.setOnClickListener(new View.OnClickListener() {
            boolean wifi=false;
            @Override
            public void onClick(View v) {
                if(!wifi)
                {
                    lWifi.setImageResource(R.drawable.ic_wifi);
                    wifi=true;
                }
                else {
                    lWifi.setImageResource(R.drawable.ic_signal_wifi_off);
                    wifi=false;
                }

            }
        });

        lExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });
    }
}
