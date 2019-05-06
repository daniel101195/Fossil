package com.example.fossil_testing;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {

    ArrayList<User> ds;
    Context context;
    UserManagement manage;

    private List<User> filteredData = null;
    private ItemFilter mFilter = new ItemFilter();

    public UserAdapter(ArrayList<User> ds, Context context) {
        this.ds = ds;
        this.context = context;
        filteredData = ds;
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

        viewHolder.bt_bookmark.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (viewHolder.bt_bookmark.getText().toString()){
                   case "Bookmark":
                       viewHolder.bt_bookmark.setText("Bookmarked");
                       User user_bookmark = ds.get(i);
                       manage = new UserManagement(context);
                       manage.ThemUser(user_bookmark);
                       break;
                   case "Bookmarked":
                       viewHolder.bt_bookmark.setText("Bookmark");
                       Integer userdebook_id = ds.get(i).user_id;
                       manage = new UserManagement(context);
                       manage.XoaUser(userdebook_id);
                       break;
               }
           }
       });

        viewHolder.bt_reputation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).ShowDetailInfo(ds.get(i).user_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_displayname;
        TextView tv_location;
        TextView tv_lastaccessdate;
        TextView tv_reputation;
        ImageView iv_avatar;
        Button bt_bookmark;
        Button bt_reputation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_displayname = itemView.findViewById(R.id.tv_name);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_reputation = itemView.findViewById(R.id.tv_reputation);
            tv_lastaccessdate = itemView.findViewById(R.id.tv_lastaccessdate);
            iv_avatar = itemView.findViewById(R.id.imageview_avatar);
            bt_bookmark = itemView.findViewById(R.id.button_bookmark);
            bt_reputation = itemView.findViewById(R.id.button_rephistory);


            bt_reputation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint!=null && constraint.length()>0){
                ArrayList<User> filterList=new ArrayList<User>();
                for(int i=0;i<filteredData.size();i++){
                    if((filteredData.get(i).getDisplay_name().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {
                        User c = new User();
                        c.display_name=filteredData.get(i).getDisplay_name();
                        c.location=filteredData.get(i).getLocation();
                        c.reputation=filteredData.get(i).getReputation();
                        c.last_access_date = filteredData.get(i).getLast_access_date();
                        c.profile_image = filteredData.get(i).getProfile_image();
                        filterList.add(c);
                    }
                }
                results.count=filterList.size();
                results.values=filterList;
            }
            else{
                results.count=filteredData.size();
                results.values=filteredData;
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ds = (ArrayList<User>) results.values;
            Log.d("nguoidung",filteredData.size()+"");
            notifyDataSetChanged();
        }

    }
}
