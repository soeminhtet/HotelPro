package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.galaxy.hotelpro.Adapter.AvailableRoomsAdapter;
import com.galaxy.hotelpro.Database.DatabaseHelper;
import com.galaxy.hotelpro.Item.AvailableRoomsItem;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.Model.AvailableRoomsModel;
import com.galaxy.hotelpro.Model.RoomChargesModel;
import com.galaxy.hotelpro.R;
import com.galaxy.hotelpro.Utility.FullScreen;
import com.galaxy.hotelpro.Utility.Json_Class;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AvailableRoomsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AvailableRoomsAdapter availableRoomsAdapter;
    GridView checkInOutGrid;
    DatabaseHelper dbHelper;
    private ArrayList<AvailableRoomsModel> selectedRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.getFullScreen(AvailableRoomsActivity.this);

        setContentView(R.layout.activity_available_rooms);

        FullScreen.getToolbar(AvailableRoomsActivity.this, R.id.availableRoom_Toolbar, "Available Rooms");

        Initialize();

        checkInOutGrid.setOnItemClickListener(this);

    }

    public void Initialize() {
        selectedRooms = new ArrayList<AvailableRoomsModel>();
        checkInOutGrid = (GridView) findViewById(R.id.availableRoomsGrid);
        dbHelper = new DatabaseHelper(AvailableRoomsActivity.this, null, null, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int selectedIndex = availableRoomsAdapter.selectedPositions.indexOf(position);
        if (selectedIndex > -1) {
            availableRoomsAdapter.selectedPositions.remove(selectedIndex);
            ((AvailableRoomsItem) view).display(false);
            selectedRooms.remove((AvailableRoomsModel) parent.getItemAtPosition(position));
        } else {
            availableRoomsAdapter.selectedPositions.add(position);
            ((AvailableRoomsItem) view).display(true);
            selectedRooms.add((AvailableRoomsModel) parent.getItemAtPosition(position));
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
                intent = new Intent(AvailableRoomsActivity.this, CheckInActivity.class);
                intent.putExtra("SELECTED_ROOM", (Serializable) selectedRooms);
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

    @Override
    protected void onStart() {
        super.onStart();
        DownloadTask downloadTask=new DownloadTask();
        String url=dbHelper.getServiceURL();
        downloadTask.execute(url+"api/data/getAvailableRooms",url+"api/data/getRoomCharges");
    }

    class DownloadTask extends AsyncTask<String, Void, List<AvailableRoomsModel>> {
        List<AvailableRoomsModel> availableRoomsList;
        List<RoomChargesModel> roomChargesList;
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            availableRoomsList= new ArrayList<AvailableRoomsModel>();
            roomChargesList= new ArrayList<RoomChargesModel>();
            progressDialog=new ProgressDialog(AvailableRoomsActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("Download");
            progressDialog.setMessage("Downloading....");
            progressDialog.show();
        }

        @Override
        protected List<AvailableRoomsModel> doInBackground(String... strings) {
            String availableUrl = strings[0];
            String roomChargesUrl = strings[1];
            try {
                JSONArray availableArray = new JSONArray(Json_Class.getJson(availableUrl));
                JSONArray roomChargesArray = new JSONArray(Json_Class.getJson(roomChargesUrl));
                for (int j = 0; j < roomChargesArray.length(); j++) {
                    JSONObject c = (JSONObject) roomChargesArray.get(j);

                    RoomChargesModel roomChargesModel = new RoomChargesModel();
                    roomChargesModel.setId(c.get("Room_TypeID").toString());
                    roomChargesModel.setDollar(c.get("Date_Dollar").toString());
                    roomChargesModel.setKyat(c.get("Date_Kyat").toString());

                    roomChargesList.add(roomChargesModel);
                }
                for (int i = 0; i < availableArray.length(); i++) {
                    JSONObject c = (JSONObject) availableArray.get(i);

                    AvailableRoomsModel checkInModel = new AvailableRoomsModel();
                    checkInModel.setSr("");
                    checkInModel.setRoom(c.get("Room_Name").toString());
                    checkInModel.setRoomType(c.get("Room_Type_Name").toString());

                    checkInModel.setBedType(c.get("Bed_Type_Name").toString());
                    checkInModel.setExtra("");

                    checkInModel.setRoomCode(c.get("Room_Code").toString());

                    boolean found = false;
                    for (int k = 0; k < roomChargesList.size() && found == false; k++) {
                        if (roomChargesList.get(k).getId().equals(c.get("Room_TypeID").toString())) {
                            checkInModel.setCharges(roomChargesList.get(k).getKyat());
                            checkInModel.setAmount(roomChargesList.get(k).getKyat());
                            checkInModel.setBalance(roomChargesList.get(k).getKyat());
                            found = true;
                        }
                    }
                    availableRoomsList.add(checkInModel);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return availableRoomsList;
        }

        @Override
        protected void onPostExecute(List<AvailableRoomsModel> list) {
            super.onPostExecute(list);
            progressDialog.dismiss();
            availableRoomsAdapter = new AvailableRoomsAdapter(getApplicationContext(), list);
            checkInOutGrid.setAdapter(availableRoomsAdapter);
        }

    }
}
