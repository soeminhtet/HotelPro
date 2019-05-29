package com.galaxy.hotelpro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galaxy.hotelpro.R;
import com.ramotion.foldingcell.FoldingCell;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    Context context;
    List<String> list;
    ArrayList<Boolean> closeFolding;
    FoldingCell currentFoldingCell;
    private onReservationItemClickListener listener;


    public ReservationAdapter(Context context, List<String> list,onReservationItemClickListener listener){
        this.list=list;
        this.context=context;
        this.listener=listener;
        closeFolding=new ArrayList<Boolean>();
        for (int i=0;i<5;i++){
            closeFolding.add(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.reservation_room_item_layout,parent,false);
        return new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView;
        onReservationItemClickListener listener;

        public ViewHolder(@NonNull View itemView,onReservationItemClickListener listener) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.reservation_title);
            this.listener=listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onReservationClick(getAdapterPosition());
        }
    }

    public interface onReservationItemClickListener{
        void onReservationClick(int position);
    }

}
