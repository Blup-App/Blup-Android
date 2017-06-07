package com.blup.android.blup.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blup.android.blup.R;
import com.blup.android.blup.Session;
import com.blup.android.blup.adaptateritem.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeActivity extends AppCompatActivity {

    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        session = new Session(getApplicationContext());
        session.getusername();
        final RecyclerView homeView = (RecyclerView) findViewById(R.id.home_card_list);

        Intent intent = getIntent();
        final String userId = intent.getStringExtra("user_id");

        final List itemsList;
        itemsList = new ArrayList();
        

        RequestQueue queue;
        queue = Volley.newRequestQueue(this);

        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, "http://192.168.1.22:8888/api/items", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("hydra:member");
                            //foreach
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                String itemOwnerId = item.getString("ownerId");

                                if(itemOwnerId.equalsIgnoreCase(userId))
                                {
                                    itemsList.add(item);
                                }

                            }

                            MyAdapter adapter = new MyAdapter()
                                    .setUserId(userId)
                                    .setItemsList(itemsList)
                                    ;

                            homeView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            homeView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(json);


    }



}
