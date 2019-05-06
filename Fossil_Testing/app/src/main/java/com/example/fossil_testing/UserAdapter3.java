package com.example.fossil_testing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter3 extends RecyclerView.Adapter<UserAdapter3.ViewHolder> {

    ArrayList<User_Reputaion> ds_reputation;
    Context context;

    public UserAdapter3(ArrayList<User_Reputaion> ds_reputation, Context context) {
        this.ds_reputation = ds_reputation;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.reputation_history, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_type.setText(ds_reputation.get(i).getType());
        viewHolder.tv_change.setText(ds_reputation.get(i).getChange());
        viewHolder.tv_creatdt.setText(ds_reputation.get(i).getCreationdt());
        viewHolder.tv_postid.setText(ds_reputation.get(i).getPost_id());
    }

    @Override
    public int getItemCount() {
        return ds_reputation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_type;
        TextView tv_creatdt;
        TextView tv_change;
        TextView tv_postid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_type = itemView.findViewById(R.id.textview_type);
            tv_creatdt = itemView.findViewById(R.id.textview_createdt);
            tv_change = itemView.findViewById(R.id.textview_change);
            tv_postid = itemView.findViewById(R.id.textview_postid);
        }
    }
}
