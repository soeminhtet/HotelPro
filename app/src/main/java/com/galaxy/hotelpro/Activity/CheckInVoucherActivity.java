package com.galaxy.hotelpro.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.galaxy.hotelpro.MainActivity;
import com.galaxy.hotelpro.R;
import com.galaxy.hotelpro.Utility.FullScreen;

public class CheckInVoucherActivity extends AppCompatActivity {

    TextView todayDate,registerNo,guestName,currency,payType,roomNo,checkInDate,checkOutDate,days,totalAmt,serviceAmt,discount,roomDiscount,
            advencePaid,advencePaidDiscount,govTax,serviceTax,roomTax,cardTax,refundAmt,netAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FullScreen.getFullScreen(this);

        setContentView(R.layout.activity_check_in_voucher);

        FullScreen.getToolbar(CheckInVoucherActivity.this,R.id.checkInVoucher_toolbar,"Check In Voucher");

        Initialization();


    }

    public void Initialization(){
        todayDate=(TextView)findViewById(R.id.todayDate);
        registerNo=(TextView)findViewById(R.id.register_no);
        guestName=(TextView)findViewById(R.id.guest_name);
        currency=(TextView)findViewById(R.id.currency);
        payType=(TextView)findViewById(R.id.payType);
        roomNo=(TextView)findViewById(R.id.roomNo);
        checkInDate=(TextView)findViewById(R.id.checkInDate);
        checkOutDate=(TextView)findViewById(R.id.checkOutDate);
        days=(TextView)findViewById(R.id.stayDay);
        totalAmt=(TextView)findViewById(R.id.totalAmount);
        serviceAmt=(TextView)findViewById(R.id.serviceAmount);
        discount=(TextView)findViewById(R.id.discount);
        roomDiscount=(TextView)findViewById(R.id.rmDiscount);
        advencePaid=(TextView)findViewById(R.id.advPaid);
        advencePaidDiscount=(TextView)findViewById(R.id.advPaidDiscount);
        govTax=(TextView)findViewById(R.id.govTax);
        serviceTax=(TextView)findViewById(R.id.serviceTax);
        roomTax=(TextView)findViewById(R.id.roomTax);
        cardTax=(TextView)findViewById(R.id.cardTax);
        refundAmt=(TextView)findViewById(R.id.refundAmt);
        netAmount=(TextView)findViewById(R.id.netAmount);

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
                intent=new Intent(CheckInVoucherActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
                break;
            case R.id.action_logout:
                intent = new Intent(CheckInVoucherActivity.this, LoginActivity.class);
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
        Intent intent = new Intent(CheckInVoucherActivity.this, CheckInActivity.class);
        intent.putExtra("SELECTED_ROOM",getIntent().getExtras().getSerializable("SELECTED_ROOM"));
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}
