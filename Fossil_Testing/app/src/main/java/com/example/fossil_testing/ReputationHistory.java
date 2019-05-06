package com.example.fossil_testing;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReputationHistory extends AppCompatActivity {
    RecyclerView rv_history;
    int user_id, page;
    ArrayList<User_Reputaion> user_reputation;
    User_Reputaion reputation_history;
    UserAdapter3 adapter3;
    String type, change, post_id, creationdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reputation_history);
        Anhxa();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_history.setLayoutManager(layoutManager);
        rv_history.setHasFixedSize(true);
        user_id = getIntent().getExtras().getInt("id");


        user_reputation = new ArrayList<>();
        adapter3 = new UserAdapter3(user_reputation, ReputationHistory.this);
        rv_history.setAdapter(adapter3);

        GetUserInfo(user_id, 1);

        rv_history.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                page += 1;
                GetUserInfo(user_id, page);
            }
        });

    }

    private void Anhxa()
    {
        rv_history = findViewById(R.id.recyclerview_history);
    }

    public void GetUserInfo(int id, int page)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.stackexchange.com/2.2/users/"+id+"/reputation-history?page="+page+"&pagesize=30&site=stackoverflow";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    final JSONArray jsonArrayItems = jsonObject.getJSONArray("items");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                for (int i=0; i < jsonArrayItems.length(); i++) {
                                    JSONObject jsonObjectItems = jsonArrayItems.getJSONObject(0);
                                    Log.d("ktreputation", "" + response);
                                    type = jsonObjectItems.getString("reputation_history_type");
                                    change = jsonObjectItems.getString("reputation_change");
                                    post_id = jsonObjectItems.getString("post_id");
                                    Long l = Long.valueOf(jsonObjectItems.getString("creation_date"));
                                    Date date = new Date(l * 1000L);
                                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                    creationdt = format.format(date);

                                    reputation_history = new User_Reputaion(type, change, post_id, creationdt);
                                    user_reputation.add(reputation_history);

                                    adapter3 = new UserAdapter3(user_reputation, getApplicationContext());
                                    rv_history.setAdapter(adapter3);
                                }

                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                            adapter3.notifyDataSetChanged();
                        }

                    }, 3000);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}
