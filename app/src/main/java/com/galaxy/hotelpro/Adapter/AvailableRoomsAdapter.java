package com.galaxy.hotelpro.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.galaxy.hotelpro.Item.AvailableRoomsItem;
import com.galaxy.hotelpro.Model.AvailableRoomsModel;

import java.util.ArrayList;
import java.util.List;

public class AvailableRoomsAdapter extends BaseAdapter {

    List<AvailableRoomsModel> roomList=new ArrayList<>();
    Context context;
    public List selectedPositions;

    public AvailableRoomsAdapter( Context context,List<AvailableRoomsModel> roomList) {
        super();
        this.roomList=roomList;
        this.context=context;
        selectedPositions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return roomList.size();
    }

    @Override
    public Object getItem(int position) {
        return roomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        AvailableRoomsItem customView = (convertView == null) ? new AvailableRoomsItem(context) : (AvailableRoomsItem) convertView;
        customView.display(roomList.get(position).getRoom(), selectedPositions.contains(position));

        return customView;
    }
}
