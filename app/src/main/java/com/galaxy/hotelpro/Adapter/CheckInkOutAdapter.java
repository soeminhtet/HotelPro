package com.galaxy.hotelpro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.galaxy.hotelpro.Model.CheckInOutModel;
import com.galaxy.hotelpro.R;

import java.util.ArrayList;
import java.util.List;

public class CheckInkOutAdapter extends BaseAdapter {

    List<CheckInOutModel> list=new ArrayList<>();
    Context context;

    public CheckInkOutAdapter(Context context, List<CheckInOutModel> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView= LayoutInflater.from(context).inflate(R.layout.available_room_item_layout,parent,false);

        TextView textView = (TextView)customView.findViewById(R.id.avRoomIdTxt);
        textView.setText(list.get(position).getRoom_Name());

        return customView;
    }

}
