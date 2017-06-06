package com.blup.android.blup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blup.android.blup.R;
import com.blup.android.blup.adaptateritem.MyAdapter;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final RecyclerView homeView = (RecyclerView) findViewById(R.id.home_card_list);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("user_id");

        MyAdapter adapter = new MyAdapter().setUserId(userId);

        homeView.setLayoutManager(new LinearLayoutManager(this));
        homeView.setAdapter(adapter);



    }



}
