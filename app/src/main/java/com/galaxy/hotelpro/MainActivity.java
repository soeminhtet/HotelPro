package com.galaxy.hotelpro;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.galaxy.hotelpro.Activity.AvailableRoomsActivity;
import com.galaxy.hotelpro.Activity.LoginActivity;
import com.galaxy.hotelpro.Activity.ReservationActivity;
import com.galaxy.hotelpro.Utility.ConnectionDetector;
import com.galaxy.hotelpro.Utility.FullScreen;
import com.galaxy.hotelpro.Utility.Global_Class;

import io.armcha.elasticview.ElasticView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ElasticView exit,reservation, checkIn,roomType,floorType,houseKeeping,lostAndFound;
    ImageView wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.getFullScreen(MainActivity.this);

        setContentView(R.layout.activity_main);

        Initialization();

        checkIn.setOnClickListener(this);
        reservation.setOnClickListener(this);
        checkIn.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    public void Initialization() {
        checkIn = (ElasticView) findViewById(R.id.checkIn_Main);
        reservation = (ElasticView) findViewById(R.id.reservation_Main);
        exit = (ElasticView) findViewById(R.id.exit_Main);
        roomType=findViewById(R.id.roomType_Main);
        floorType=findViewById(R.id.floorType_Main);
        houseKeeping=findViewById(R.id.houseKeeping_Main);
        lostAndFound=findViewById(R.id.lostAndFound_Main);
        wifi=findViewById(R.id.mainWifiImg);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        Intent intent;
        switch (id){
            case R.id.checkIn_Main:
                intent = new Intent(MainActivity.this, AvailableRoomsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;

            case R.id.reservation_Main:
                intent = new Intent(MainActivity.this, ReservationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;

            case R.id.exit_Main:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        new DownloadTask().execute();
    }

    class DownloadTask extends AsyncTask<Void,Void,Boolean> {


        @Override
        protected Boolean doInBackground(Void... voids) {
            return Global_Class.CheckConnection(MainActivity.this);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
            {
                wifi.setImageResource(R.drawable.ic_main_wifi);
            } else {
                wifi.setImageResource(R.drawable.ic_signal_wifi_off);
            }
        }
    }
}
