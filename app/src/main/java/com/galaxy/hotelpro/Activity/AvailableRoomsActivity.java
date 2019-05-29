package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.galaxy.hotelpro.Adapter.AvailableRoomsAdapter;
import com.galaxy.hotelpro.Item.AvailableRoomsItem;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.Model.CheckInModel;
import com.galaxy.hotelpro.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AvailableRoomsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AvailableRoomsAdapter availableRoomsAdapter;

    GridView checkInOutGrid;
    Toolbar toolbar;
    private ArrayList<CheckInModel> selectedRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_available_rooms);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Initialize();

        final List<CheckInModel> list=new ArrayList<CheckInModel>();
        list.add(new CheckInModel("1","Single","001","Single","10000","10000","1","10000"));
        list.add(new CheckInModel("2","Double","002","Double","20000","10000","1","20000"));
        list.add(new CheckInModel("3","King","003","King","400000","400000","1","400000"));
        list.add(new CheckInModel("4","Queen","004","Queen","600000","600000","1","10000"));
        list.add(new CheckInModel("5","Single","005","Single","10000","10000","1","10000"));

        availableRoomsAdapter=new AvailableRoomsAdapter(list,getApplicationContext());
        checkInOutGrid.setAdapter(availableRoomsAdapter);
        checkInOutGrid.setOnItemClickListener(this);

    }

    public void Initialize(){
        selectedRooms=new ArrayList<CheckInModel>();
        checkInOutGrid=(GridView)findViewById(R.id.availableRoomsGrid);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int selectedIndex = availableRoomsAdapter.selectedPositions.indexOf(position);
        if (selectedIndex > -1) {
            availableRoomsAdapter.selectedPositions.remove(selectedIndex);
            ((AvailableRoomsItem) view).display(false);
            selectedRooms.remove((CheckInModel) parent.getItemAtPosition(position));
        } else {
            availableRoomsAdapter.selectedPositions.add(position);
            ((AvailableRoomsItem) view).display(true);
            selectedRooms.add((CheckInModel) parent.getItemAtPosition(position));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_checkin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.submitBtn:
                intent=new Intent(AvailableRoomsActivity.this,CheckInActivity.class);
                intent.putExtra("SELECTED_ROOM",(Serializable)selectedRooms);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            break;
            case R.id.action_logout:
                intent = new Intent(AvailableRoomsActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AvailableRoomsActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
