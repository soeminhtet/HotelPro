package com.galaxy.hotelpro.Activity;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.galaxy.hotelpro.Database.DatabaseHelper;
import com.galaxy.hotelpro.Dialogs.LoginSetting_Dialog;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.Model.PosUserModel;
import com.galaxy.hotelpro.R;
import com.galaxy.hotelpro.Utility.Custom_Snackbar;
import com.galaxy.hotelpro.Utility.FullScreen;
import com.galaxy.hotelpro.Utility.Global_Class;
import com.galaxy.hotelpro.Utility.Json_Class;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView setting,wifi;
    LinearLayout linearLayout;
    MaterialButton exit,login;
    AutoCompleteTextView username;
    EditText password;
    DatabaseHelper dbHelper;
    List<PosUserModel> posUserList;
    String pws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.getFullScreen(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_login);

        Initialize();

        username.setOnClickListener(this);
        setting.setOnClickListener(this);
        wifi.setOnClickListener(this);
        exit.setOnClickListener(this);
        login.setOnClickListener(this);
        username.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pws=posUserList.get(position).get_password();
            }
        });
    }

    private void Initialize() {

        dbHelper=new DatabaseHelper(this,null,null,0);

        setting=findViewById(R.id.settingImg_Login);
        wifi=findViewById(R.id.wifiImg_Login);
        exit=findViewById(R.id.exitBtn_Login);
        login=findViewById(R.id.loginBtn_Login);
        username=findViewById(R.id.etUsername);
        linearLayout=findViewById(R.id.loginLayout);
        password=findViewById(R.id.etPassword);
        posUserList=new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        LoginSetting_Dialog dialog=new LoginSetting_Dialog(LoginActivity.this);
        switch (id){
            case R.id.etUsername:
                username.showDropDown();
                break;
            case R.id.settingImg_Login:
                float deg = setting.getRotation() + 360F;
                setting.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                dialog.show();
                break;
            case R.id.wifiImg_Login:
                break;
            case R.id.loginBtn_Login:
                if(Global_Class.CheckConnection(LoginActivity.this)){
                    if (!username.getText().toString().trim().equals("")){
                        if (pws.equals(password.getText().toString().trim()))
                        {
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        }
                        else Custom_Snackbar.getSnackbar(this,linearLayout,R.drawable.snackbar_warning_background,
                                R.color.danger,R.drawable.ic_incorrectpws,"Incorrect Password");
                    }else Custom_Snackbar.getSnackbar(this,linearLayout,R.drawable.snackbar_warning_background,
                            R.color.danger,R.drawable.ic_incorrectpws,"Pls Enter Username");
                }
                else {
                    Custom_Snackbar.getSnackbar(this,linearLayout,R.drawable.snackbar_danger_background,R.color.danger,
                            R.drawable.ic_no_wifi,"No wifi connection");
                }
                break;
            case R.id.exitBtn_Login:
                System.exit(1);
                break;
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                new DownloadTask().execute();
                setPosUser();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        new DownloadTask().execute();
        setPosUser();
    }



    public void setPosUser(){
        posUserList=dbHelper.getAllPosUsers();
        List<String> posUserName=new ArrayList<>();
        for (int i=0;i<posUserList.size();i++){
            posUserName.add(posUserList.get(i).get_name());
        }
        ArrayAdapter guestAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,posUserName);
        username.setAdapter(guestAdapter);
    }


    class DownloadTask extends AsyncTask<Void,Void,Boolean>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(LoginActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Connect");
            progressDialog.setMessage("Connecting....");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return Global_Class.CheckConnection(LoginActivity.this);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.dismiss();
            if (aBoolean)
            {
                wifi.setImageResource(R.drawable.ic_wifi);
            } else {
                wifi.setImageResource(R.drawable.ic_signal_wifi_off);
            }
        }
    }
}
