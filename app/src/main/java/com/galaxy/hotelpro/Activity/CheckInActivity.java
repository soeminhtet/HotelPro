package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import com.galaxy.hotelpro.Adapter.CheckInRecyclerViewAdapter;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.Model.CheckInModel;
import com.galaxy.hotelpro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CheckInActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText checkInDateEt,checkOutEt,nightEt,exchangeRateEt;
    AppCompatAutoCompleteTextView guestName;
    TextInputLayout checkInDateTi,exchangeRateTi;
    RecyclerView recyclerView;
    MaterialButton nightPlusBtn,nightMinusBtn;

    CheckInRecyclerViewAdapter checkInReyclerViewAdapter;

    Date dateTemp;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    ArrayAdapter<String> spCurrencyAdapter,spPayTypeAdapter,guestAdapter;
    AppCompatSpinner spCurrency,spPayType;
    List<CheckInModel> list;

    private ArrayList<CheckInModel> selectedRooms=new ArrayList<>();

    final String[] currencyList = {"Currency","Kyat","Usd","Baht"};
    final String[] payTypeList={"Paytype","Cash","Credit","Card"};
    final String[] guestNameList={"Soe Min Htet","Zin Min","Min Khant Kyaw","Aung Thin","Soe Min Htet","Zin Min","Min Khant Kyaw","Aung Thin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_check_in);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Check In");

        Initialize();

       /* spCurrencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPayTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCurrency.setAdapter(spCurrencyAdapter);
        spPayType.setAdapter(spPayTypeAdapter);*/
        guestName.setAdapter(guestAdapter);

        checkInReyclerViewAdapter=new CheckInRecyclerViewAdapter(this,selectedRooms);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(checkInReyclerViewAdapter);

        guestName.setOnClickListener(this);

        checkInDateEt.setOnClickListener(this);
        nightPlusBtn.setOnClickListener(this);
        nightMinusBtn.setOnClickListener(this);

        nightEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(dateTemp==null){
                    dateTemp=myCalendar.getTime();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()){
                    s="0";
                }
                Date addDate = addDay(dateTemp, Integer.parseInt(s.toString()));
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                checkOutEt.setText(sdf.format(addDate));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        /*spCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (currencyList[position].equals("Usd")){
                    exchangeRateEt.setClickable(true);
                    exchangeRateEt.setText("1000");
                    exchangeRateEt.setVisibility(View.VISIBLE);
                }
                else if (currencyList[position].equals("Baht")){
                    exchangeRateEt.setClickable(true);
                    exchangeRateEt.setVisibility(View.VISIBLE);
                    exchangeRateEt.setText("48");
                }
                else {
                    exchangeRateEt.setClickable(false);
                    exchangeRateTi.setClickable(false);
                    exchangeRateEt.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

    }

    public void Initialize(){
        spCurrencyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, currencyList);
        spPayTypeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, payTypeList);
        guestAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,guestNameList);

        //exchangeRateEt=(TextInputEditText) findViewById(R.id.chiExchangeRateEt);
        checkInDateEt=(TextInputEditText)findViewById(R.id.chiCheckInDateEt);
        checkInDateTi=(TextInputLayout)findViewById(R.id.chiCheckInDateTi);
        checkOutEt=(TextInputEditText)findViewById(R.id.chiCheckOutEt);
        nightEt=(TextInputEditText)findViewById(R.id.chiNoNightEt);
        guestName=(AppCompatAutoCompleteTextView) findViewById(R.id.chiGuestNameTv);
        //exchangeRateTi=(TextInputLayout)findViewById(R.id.chiExchangeRateTi);

        nightPlusBtn=(MaterialButton)findViewById(R.id.nightPlusBtn);
        nightMinusBtn=(MaterialButton)findViewById(R.id.nightMinusBtn);

        recyclerView=(RecyclerView)findViewById(R.id.chiRecyclerView);

        /*spCurrency = (AppCompatSpinner) findViewById(R.id.spCurrency);
        spPayType =(AppCompatSpinner) findViewById(R.id.spPayType);*/
        myCalendar = Calendar.getInstance();

        selectedRooms.clear();
        selectedRooms=(ArrayList<CheckInModel>) getIntent().getExtras().getSerializable("SELECTED_ROOM");

        if (selectedRooms.size()<6){
            int loop=6-selectedRooms.size();
            for(int i=0;i<loop;i++){
                selectedRooms.add(new CheckInModel("","","","","","","",""));
            }
        }

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

    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
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
                break;
            case R.id.nightMinusBtn:
                    count=Integer.parseInt(nightEt.getText().toString());
                    if (count>0)
                    {
                        nightEt.setText(String.valueOf(--count));
                    }
                break;
        }
    }

}
