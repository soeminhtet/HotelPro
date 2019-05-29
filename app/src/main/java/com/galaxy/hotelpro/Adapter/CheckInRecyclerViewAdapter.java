package com.galaxy.hotelpro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.galaxy.hotelpro.Model.CheckInModel;
import com.galaxy.hotelpro.R;

import java.util.ArrayList;
import java.util.List;

public class CheckInRecyclerViewAdapter extends RecyclerView.Adapter {

    List<CheckInModel> list=new ArrayList<>();
    Context context;

    public CheckInRecyclerViewAdapter(Context context, List<CheckInModel> list){
        this.context=context;
        this.list=list;
    }

    public class RowViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtSr,txtRoomType,txtRoom,txtBedType,txtCharges,txtAmount,txtExtra,txtBalance;

        public RowViewHolder(View itemView) {
            super(itemView);

            txtSr = (TextView)itemView.findViewById(R.id.availableRoomSr);
            txtRoom = (TextView)itemView.findViewById(R.id.availableRoomRoom);
            txtRoomType =(TextView) itemView.findViewById(R.id.availableRoomType);
            txtBedType=(TextView)itemView.findViewById(R.id.availableRoomBedType);
            txtCharges = (TextView)itemView.findViewById(R.id.availableRoomCharges);
            txtAmount = (TextView)itemView.findViewById(R.id.availableRoomAmount);
            txtExtra =(TextView) itemView.findViewById(R.id.availableRoomExtra);
            txtBalance=(TextView)itemView.findViewById(R.id.availableRoomBalance);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.check_in_recycler_item_layout, parent, false);

        return new RowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RowViewHolder rowViewHolder = (RowViewHolder) holder;

        rowViewHolder.txtSr.setText(list.get(position).getSr().toString());
        rowViewHolder.txtRoomType.setText(list.get(position).getRoomType().toString());
        rowViewHolder.txtRoom.setText(list.get(position).getRoom().toString());
        rowViewHolder.txtBedType.setText(list.get(position).getBedType().toString());
        rowViewHolder.txtCharges.setText(list.get(position).getCharges().toString());
        rowViewHolder.txtAmount.setText(list.get(position).getAmount().toString());
        rowViewHolder.txtExtra.setText(list.get(position).getExtra().toString());
        rowViewHolder.txtBalance.setText(list.get(position).getBalance().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

