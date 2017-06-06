package com.blup.android.blup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blup.android.blup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemsActivity extends AppCompatActivity {
    TextView itemsView;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        itemsView = (TextView) findViewById(R.id.itemsView);
        queue = Volley.newRequestQueue(this);

        final JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, "http://192.168.1.38:8888/api/items", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("hydra:member");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                String title = item.getString("title");
                                String img = item.getString("img");
                                int grade = item.getInt("grade");
                                String owner = item.getString("owner");
                                itemsView.append("Title : " + title + " Link img : " + img + " Note : " + grade + " Owner : " + owner + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );
        queue.add(json);
    }
};