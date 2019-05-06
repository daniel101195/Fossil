package com.example.fossil_testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    RecyclerView rv_bookmark;
    UserAdapter2 adapter2;
    ArrayList<User> arrayList_userbookmark;
    UserManagement manage2;
    Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        rv_bookmark = findViewById(R.id.recyclerview_bookmark);
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        tb.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_bookmark.setLayoutManager(layoutManager);
        manage2 = new UserManagement(Main2Activity.this);
        arrayList_userbookmark = new ArrayList<>();
        arrayList_userbookmark = manage2.LayCongviec();


        try {
            adapter2 = new UserAdapter2(arrayList_userbookmark, Main2Activity.this);
            rv_bookmark.setAdapter(adapter2);
        }catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
