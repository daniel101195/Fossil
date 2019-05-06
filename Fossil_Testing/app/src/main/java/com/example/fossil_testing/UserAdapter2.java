package com.example.fossil_testing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter2 extends RecyclerView.Adapter<UserAdapter2.ViewHolder> {

    ArrayList<User> ds;
    Context context;
    UserManagement manage;

    public UserAdapter2(ArrayList<User> ds, Context context) {
        this.ds = ds;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_displayname.setText(ds.get(i).getDisplay_name());
        viewHolder.tv_location.setText(ds.get(i).getLocation());
        viewHolder.tv_reputation.setText(ds.get(i).getReputation());
        viewHolder.tv_lastaccessdate.setText(ds.get(i).getLast_access_date());
        Picasso.with(context).load(String.valueOf(ds.get(i).getProfile_image())).into(viewHolder.iv_avatar); //Load Icon path to ImageView
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_displayname;
        TextView tv_location;
        TextView tv_lastaccessdate;
        TextView tv_reputation;
        ImageView iv_avatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_displayname = itemView.findViewById(R.id.tv_name);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_reputation = itemView.findViewById(R.id.tv_reputation);
            tv_lastaccessdate = itemView.findViewById(R.id.tv_lastaccessdate);
            iv_avatar = itemView.findViewById(R.id.imageview_avatar);
        }
    }
}
