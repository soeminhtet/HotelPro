package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.galaxy.hotelpro.Adapter.ReservationAdapter;
import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.R;
import com.galaxy.hotelpro.Utility.FullScreen;
import com.nicolettilu.hiddensearchwithrecyclerview.HiddenSearchWithRecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ReservationActivity extends AppCompatActivity implements ReservationAdapter.onReservationItemClickListener{

    HiddenSearchWithRecyclerView hiddenSearchWithRecyclerView;
    RecyclerView recyclerView;
    ReservationAdapter reservationAdapter;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.getFullScreen(this);

        setContentView(R.layout.activity_reservation);

        FullScreen.getToolbar(ReservationActivity.this,R.id.reservation_toolbar,"Reservation");

        Initialization();

        reservationAdapter=new ReservationAdapter(ReservationActivity.this,list,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(reservationAdapter);
    }

    public void Initialization(){
        list=new ArrayList<>();
        hiddenSearchWithRecyclerView=(HiddenSearchWithRecyclerView)findViewById(R.id.hiddenSearch);
        hiddenSearchWithRecyclerView.setHideAtScroll(true);
        hiddenSearchWithRecyclerView.setVisibleAtInit(false);
        hiddenSearchWithRecyclerView.setScrollToBottomBeforeHide(false);
        hiddenSearchWithRecyclerView.setScrollToTopBeforeShow(false);
        hiddenSearchWithRecyclerView.setFilterWhileTyping(true);

        recyclerView=(RecyclerView)findViewById(R.id.reservationRecycler);

        list=new ArrayList<>();
        list.add("Soe Min Htet");
        list.add("Zin Min");
        list.add("Khant Khant");
        list.add("Aung Aung");
        list.add("Mg Mg ");
        list.add("Kaung Kaung");
        list.add("Zaw Min Phyo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reservation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent;
        switch (id){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_logout:
                intent = new Intent(ReservationActivity.this, LoginActivity.class);
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
        Intent intent = new Intent(ReservationActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    public void onReservationClick(int position) {
        /*Intent intent=new Intent(ReservationActivity.this,CheckInActivity.class);
        startActivity(intent);*/
    }
}
