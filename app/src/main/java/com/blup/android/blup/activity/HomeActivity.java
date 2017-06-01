package com.blup.android.blup.activity;

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

        final RecyclerView homeList = (RecyclerView) findViewById(R.id.home_card_list);

        homeList.setLayoutManager(new LinearLayoutManager(this));
        homeList.setAdapter(new MyAdapter());



    }



}
