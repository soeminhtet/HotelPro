package com.galaxy.hotelpro.Item;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.galaxy.hotelpro.R;


public class AvailableRoomsItem extends FrameLayout {

    TextView textView;
    CardView cardView;
    RelativeLayout relativeLayout;

    public AvailableRoomsItem(@NonNull Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.available_room_item_layout, this);
        textView = (TextView) getRootView().findViewById(R.id.avRoomIdTxt);
        cardView=(CardView)getRootView().findViewById(R.id.avRoomIdCardView);
        relativeLayout=(RelativeLayout)getRootView().findViewById(R.id.avRoomIdRelative);
    }

    public void display(String text, boolean isSelected) {
        textView.setText(text);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        cardView.setBackgroundColor(isSelected ? Color.parseColor("#4dd0e1") :
                Color.parseColor("#03A9F4"));
        relativeLayout.setBackgroundColor(isSelected ? Color.parseColor("#4dd0e1") :
                Color.parseColor("#03A9F4"));
    }
}
