package com.example.fossil_testing;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    EditText et_search;
    ProgressBar pb;
    Button bt_listuserbookmark;
    ArrayList<User> arraylist_user;
    UserAdapter adapter;
    String username, location, reputation, lastaccessdate, link;
    int user_id = 0;
    User user;
    UserManagement qlnv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.RecylerView);
        pb = findViewById(R.id.item_progress_bar);
        et_search = findViewById(R.id.editText_search);
        bt_listuserbookmark = findViewById(R.id.button_listuserbookmark);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        qlnv = new UserManagement(MainActivity.this);

        arraylist_user = new ArrayList<>();
        adapter = new UserAdapter(arraylist_user, MainActivity.this);
        rv.setAdapter(adapter);

        addDataToList(1);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rv.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                //addDataToList(data++);
                for (int i=2; i<100; i++)
                {
                    addDataToList(i);
                }
            }
        });

        bt_listuserbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

    }

    public void addDataToList(int data) {
        pb.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://api.stackexchange.com/2.2/users?page="+data+"&pagesize=30&site=stackoverflow";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    final JSONArray jsonArrayItems = jsonObject.getJSONArray("items");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                    for (int i=0; i<jsonArrayItems.length(); i++) {
                                        JSONObject jsonObjectItems = jsonArrayItems.getJSONObject(i);
                                        username = jsonObjectItems.getString("display_name");
                                        location = jsonObjectItems.getString("location");
                                        reputation = jsonObjectItems.getString("reputation");
                                        user_id = jsonObjectItems.getInt("user_id");
                                        Long l = Long.valueOf(jsonObjectItems.getString("last_access_date"));
                                        Date date = new Date(l * 1000L);
                                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                        lastaccessdate = format.format(date);
                                        link = jsonObjectItems.getString("profile_image");

                                        user = new User(username, location, reputation, link, lastaccessdate, user_id);
                                        arraylist_user.add(user);

                                        adapter = new UserAdapter(arraylist_user, MainActivity.this);
                                        rv.setAdapter(adapter);
                                    }

                                    adapter.notifyDataSetChanged();
                                    pb.setVisibility(View.GONE);

                                } catch (JSONException e){
                                        e.printStackTrace();
                            }
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

    public void ShowDetailInfo(int user_id)
    {
        Intent i = new Intent(MainActivity.this, ReputationHistory.class);
        i.putExtra("id", user_id);
        startActivity(i);
    }

}











