package com.galaxy.hotelpro.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.icu.util.BuddhistCalendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.galaxy.hotelpro.Activity.LoginActivity;
import com.galaxy.hotelpro.Database.DatabaseHelper;
import com.galaxy.hotelpro.R;
import com.galaxy.hotelpro.Utility.ConnectionDetector;
import com.galaxy.hotelpro.Utility.Global_Class;
import com.galaxy.hotelpro.Utility.Json_Class;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginSetting_Dialog extends Dialog implements View.OnClickListener {

    TextInputEditText url,register,portNo;
    MaterialButton unRegister,loadData,resetData;
    DatabaseHelper dbHelper;

    Context context;
    public LoginSetting_Dialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_setting_dialog);

        Initialize();

        loadData.setOnClickListener(this);

    }

    private void Initialize() {
        url=findViewById(R.id.urlEt_LoginDialog);
        register=findViewById(R.id.registerEt_LoginDialog);
        portNo=findViewById(R.id.portNoEt_LoginDialog);
        unRegister=findViewById(R.id.unRegisterBtn_LoginDialog);
        loadData=findViewById(R.id.loadDataBtn_LoginDialog);
        resetData=findViewById(R.id.resetDataBtn_LoginDialog);

        dbHelper = new DatabaseHelper(context,null,null,0);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.loadDataBtn_LoginDialog:
                try {
                    Register(register.getText().toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dbHelper.AddServiceAddress(url.getText().toString().trim(), "", "");
                dbHelper.AddPortAddress(portNo.getText().toString().trim());
                dbHelper.addPosUser(dbHelper.getServiceURL()+"/api/Posuser");
                dismiss();
                break;
            case R.id.resetDataBtn_LoginDialog:
                /*String value = url.getText().toString().trim();
                dbhelper.AddServiceAddress(value, "", "");
                new LoginActivity().checkConnectionStatus();
                Toast.makeText(context,"True",Toast.LENGTH_SHORT).show();*/
                break;
        }
    }

    public void Register(String DeviceName) throws NumberFormatException, JSONException {

        if (DeviceName.matches("")) {
            Toast.makeText(context, "Please, Enter your device name",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println(Global_Class.GetTabletID()+ " /   / " +
                Build.DISPLAY + " /  / "
        + DeviceName);

        if (Build.DISPLAY.equals("")){
            Toast.makeText(getContext(),Build.DISPLAY,Toast.LENGTH_SHORT).show();
        }
        /*JSONArray salejsonarray = new JSONArray();
        JSONObject jsonobj = new JSONObject();
        // sale_head_main
        jsonobj.put("DeviceID", Global_Class.GetTabletID());
        jsonobj.put("DeviceBuildNumber", Build.DISPLAY);
        jsonobj.put("DeviceName", DeviceName);

        salejsonarray.put(jsonobj);*/

        /*if (Global_Class.CheckConnection(context)) {

            String result= Json_Class.getJson(new DatabaseHelper(context,null,null,0)
                    .getServiceURL()
                    + "/Data/Register?orderdata="
                    + java.net.URLEncoder.encode(salejsonarray.toString()));

            JSONArray jsonmessage=new JSONArray(result);

            if (jsonmessage.length() > 0) {
                if (Integer.parseInt(jsonmessage.get(0).toString()) == 0) {
                    dbHelper.updateRegisterFlag(true, DeviceName);
                    Toast.makeText(context, jsonmessage.get(1)
                            .toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,jsonmessage.get(2)
                            .toString(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(context,"No Connection with Server!",
                    Toast.LENGTH_SHORT).show();
        }
        Global_Class.CheckConnection(context);*/
    }
}
