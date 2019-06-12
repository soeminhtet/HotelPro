package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import com.galaxy.hotelpro.Adapter.CheckInRecyclerViewAdapter;
import com.galaxy.hotelpro.Database.DatabaseHelper;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.Model.AvailableRoomsModel;
import com.galaxy.hotelpro.Model.GuestInfoModel;
import com.galaxy.hotelpro.R;
import com.galaxy.hotelpro.Utility.FullScreen;
import com.galaxy.hotelpro.Utility.Json_Class;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckInActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    TextInputEditText checkInDateEt,checkOutEt,nightEt;
    AppCompatAutoCompleteTextView guestName;
    TextInputLayout checkInDateTi;
    RecyclerView recyclerView;
    MaterialButton nightPlusBtn,nightMinusBtn;
    TextView totalAmount,discountPercent,discountAmount,taxPercent,taxAmount,netAmount;
    CheckInRecyclerViewAdapter checkInRecyclerViewAdapter;
    Date dateTemp;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    ArrayAdapter<String> guestAdapter;
    DatabaseHelper dbHelper;
    int selectedOriginalSize;
    ArrayList<GuestInfoModel> guestInfoList;
    double amount,discount,tax,net,originalDiscount,originalTax;
    private ArrayList<AvailableRoomsModel> selectedRooms=new ArrayList<>();

    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.getFullScreen(this);

        setContentView(R.layout.activity_check_in);

        FullScreen.getToolbar(CheckInActivity.this,R.id.checkIn_toolbar,"Check In");

        Initialize();

        getTotalNetAmount();

        checkInRecyclerViewAdapter=new CheckInRecyclerViewAdapter(this,selectedRooms);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(checkInRecyclerViewAdapter);

        guestName.setOnClickListener(this);
        checkInDateEt.setOnClickListener(this);
        nightPlusBtn.setOnClickListener(this);
        nightMinusBtn.setOnClickListener(this);
        nightEt.addTextChangedListener(this);
        guestName.addTextChangedListener(this);

    }

    public void Initialize(){

        dbHelper=new DatabaseHelper(this,null,null,0);

        guestInfoList=new ArrayList<>();

        totalAmount=findViewById(R.id.totalAmount_CheckIn);
        discountPercent=findViewById(R.id.discountPercent_CheckIn);
        discountAmount=findViewById(R.id.totalDiscount_CheckIn);
        taxPercent=findViewById(R.id.taxPercent_CheckIn);
        taxAmount=findViewById(R.id.totalTax_CheckIn);
        netAmount=findViewById(R.id.totalNetAmount_CheckIn);
        checkInDateEt=findViewById(R.id.chiCheckInDateEt);
        checkInDateTi=findViewById(R.id.chiCheckInDateTi);
        checkOutEt=findViewById(R.id.chiCheckOutEt);
        nightEt=findViewById(R.id.chiNoNightEt);
        guestName=findViewById(R.id.chiGuestNameTv);
        nightPlusBtn=findViewById(R.id.nightPlusBtn);
        nightMinusBtn=findViewById(R.id.nightMinusBtn);
        recyclerView=findViewById(R.id.chiRecyclerView);
        myCalendar = Calendar.getInstance();
        selectedRooms.clear();
        selectedRooms=(ArrayList<AvailableRoomsModel>) getIntent().getExtras().getSerializable("SELECTED_ROOM");
        selectedOriginalSize=selectedRooms.size();
        setSelectedSize();

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                checkInDateEt.setText(sdf.format(myCalendar.getTime()));
                checkOutEt.setText(sdf.format(addDay(myCalendar.getTime(),
                        nightEt.getText().toString().trim().equals("")? 0 : Integer.parseInt(nightEt.getText().toString().trim()))));
                dateTemp=myCalendar.getTime();
            }

        };
        nightEt.setText("1");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_checkin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent;
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.submitBtn:
                intent=new Intent(CheckInActivity.this, MainActivity.class);
                //intent.putExtra("SELECTED_ROOM",(Serializable)selectedRooms);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.action_logout:
                intent = new Intent(CheckInActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CheckInActivity.this, AvailableRoomsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        int count=0;
        switch (id)
        {
            case R.id.chiGuestNameTv:
                    guestName.showDropDown();
                break;
            case R.id.chiCheckInDateEt:
                    DatePickerDialog datePickerDialog = new DatePickerDialog(CheckInActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                    datePickerDialog.getWindow().setLayout(700,850);
                break;
            case R.id.chiNoNightEt:
                break;
            case R.id.chiCheckOutEt:
                break;
            case R.id.nightPlusBtn:
                    count=Integer.parseInt(nightEt.getText().toString());
                    nightEt.setText(String.valueOf(++count));
                    refreshTable(count);
                    getTotalNetAmount();
                break;
            case R.id.nightMinusBtn:
                    count=Integer.parseInt(nightEt.getText().toString());
                    if (count>0)
                    {
                        nightEt.setText(String.valueOf(--count));
                        refreshTable(count);
                        getTotalNetAmount();
                    }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        checkInDateEt.setText(sdf.format(myCalendar.getTime()));
        checkOutEt.setText(sdf.format(addDay(myCalendar.getTime(),1)));
        DownloadTask downloadTask=new DownloadTask();
        String url=dbHelper.getServiceURL();
        downloadTask.execute(url+"api/data/getGuestInfo",url+"api/data/getData");
    }

    public void getTotalNetAmount(){
        amount=0;
        for (int i=0; i<selectedOriginalSize; i++){
            amount+=Double.parseDouble(selectedRooms.get(i).getBalance().toString());
        }
        discount=(amount*(originalDiscount*0.01)*0.01);
        tax=((amount-discount)*originalTax)*0.01;
        net=amount+discount+tax;
        totalAmount.setText(String.format("%.1f",amount));
        discountAmount.setText(String.format("%.1f",discount));
        taxAmount.setText(String.format("%.1f",tax));
        netAmount.setText(String.format("%.1f",net));
    }

    private void setSelectedSize(){
        if (selectedRooms.size()<5){
            int loop=5-selectedRooms.size();
            for(int i=0;i<loop;i++){
                selectedRooms.add(new AvailableRoomsModel("","","","","","","","",""));
            }
        }
    }

    private void refreshTable(int count){
        ArrayList<AvailableRoomsModel> temp=new ArrayList<>();
        for(int i=0;i<selectedOriginalSize;i++){
            temp.add(selectedRooms.get(i));
        }
        selectedRooms.clear();
        for(int i=0; i<temp.size();i++){
            AvailableRoomsModel tempModel=new AvailableRoomsModel();
            tempModel.setSr(temp.get(i).getSr());
            tempModel.setRoomType(temp.get(i).getRoomType());
            tempModel.setRoom(temp.get(i).getRoom());
            tempModel.setRoomCode(temp.get(i).getRoomCode());
            tempModel.setBedType(temp.get(i).getBedType());
            tempModel.setCharges(temp.get(i).getCharges());
            String[] output=temp.get(i).getCharges().split("\\.");
            int amount=Integer.parseInt(output[0].trim().toString())*count;
            tempModel.setAmount( String.valueOf(amount)+".0" );
            tempModel.setExtra("");
            tempModel.setBalance(String.valueOf(amount)+".0");

            selectedRooms.add(tempModel);
        }
        setSelectedSize();
        checkInRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (nightEt.getText().hashCode()==s.hashCode()){
            if(dateTemp==null){
                dateTemp=myCalendar.getTime();
            }
        }else if (guestName.getText().hashCode()==s.hashCode()){

        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (nightEt.getText().hashCode()==s.hashCode()){

            if (s.toString().isEmpty()){
                s="0";
            }
            Date addDate = addDay(dateTemp, Integer.parseInt(s.toString()));
            String myFormat = "MM/dd/yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            checkOutEt.setText(sdf.format(addDate));

        }else if (guestName.getText().hashCode()==s.hashCode()){

        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (nightEt.getText().hashCode()==s.hashCode()){

        }else if (guestName.getText().hashCode()==s.hashCode()){
            String name=guestName.getText().toString();
            boolean found=false;
            for (int i=0; i<guestInfoList.size() && found==false; i++){
                if (guestInfoList.get(i).getName().equals(name)){
                    originalDiscount=Double.parseDouble(guestInfoList.get(i).getDispercent().toString());
                    found=true;
                }
            }
            discount=(amount*(originalDiscount*0.01)*0.01);
            discountAmount.setText(String.format("%.1f",discount));

            tax=((amount-discount)*originalTax)*0.01;
            taxAmount.setText(String.format("%.1f",tax));

            net=amount+discount+tax;
            netAmount.setText(String.format("%.1f",net));
        }
    }


    class DownloadTask extends AsyncTask<String,Void,List<String>> {

        ArrayList<String> guestNameList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            guestNameList=new ArrayList<>();
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            String url=strings[0];
            String taxUrl=strings[1];
            String result= Json_Class.getJson(url);
            String taxResult = Json_Class.getJson(taxUrl);
            if (result!=null){
                try {
                    JSONArray taxJsonArray=new JSONArray(taxResult);
                    JSONObject taxJsonObject=(JSONObject)taxJsonArray.get(0);
                    originalTax=Double.parseDouble(taxJsonObject.get("Tax_Percent").toString())*0.01;
                    JSONArray guestInfoArray = new JSONArray(result);
                    for (int j = 0; j < guestInfoArray.length(); j++) {
                        JSONObject c = (JSONObject) guestInfoArray.get(j);

                        GuestInfoModel guestInfoModel = new GuestInfoModel();
                        guestInfoModel.setName(c.get("Name").toString());
                        guestInfoModel.setDispercent(c.get("Dis_Percent").toString());

                        guestInfoList.add(guestInfoModel);
                    }
                    for (int i=0; i<guestInfoList.size(); i++){
                        guestNameList.add(guestInfoList.get(i).getName().toString());
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
            return guestNameList;
        }

        @Override
        protected void onPostExecute(List<String> s) {
            super.onPostExecute(s);
            guestAdapter=new ArrayAdapter<String>(CheckInActivity.this,
                    android.R.layout.simple_list_item_1,s);
            guestName.setAdapter(guestAdapter);
        }
    }

}
