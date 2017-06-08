package com.blup.android.blup.activity;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blup.android.blup.R;
import com.blup.android.blup.custom.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ItemCreateActivity extends AppCompatActivity  {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText title;
    private EditText note;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_create);

        LinearLayout mImageView = (LinearLayout) findViewById(R.id.item_create_picture_btn);
        title = (EditText) findViewById(R.id.item_add_title);
        note = (EditText) findViewById(R.id.item_add_note);
        submit = (Button) findViewById(R.id.item_add_submit);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(title, InputMethodManager.HIDE_IMPLICIT_ONLY);
        imm.showSoftInput(note, InputMethodManager.HIDE_IMPLICIT_ONLY);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject jsonBody = new JSONObject();
                 //   jsonBody.put("ownerId", new Session(ItemCreateActivity.this).getuserid());
                    jsonBody.put("title", title.getText().toString());
                    jsonBody.put("note", note.getText().toString());
                    jsonBody.put("token", "ki213ju@h!ygvb12321hl21nm");
                    jsonBody.put("adress", "Paris, France");
                    jsonBody.put("returnDate", "2017-06-08T04:04:49.230Z");
                    jsonBody.put("lendDate", "2017-06-08T04:04:49.230Z");
                    jsonBody.put("reminder", "2017-06-08T04:04:49.230Z");


                    RequestQueue queue;
//                     queue = Volley.newRequestQueue(ItemCreateActivity.this);

                    JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST, "http://192.168.1.20:8000/api/items", jsonBody,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    if("" != null){}
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if("" != null){}
                        }
                    }){
                        @Override
                        public Map<String, String> getHeaders()
                        {
                            Map<String, String>  params = new HashMap<>();
                            // the POST parameters:
                            params.put("Content-Type", "application/ld+json; text/html");
                            return params;
                        }
                    };
       //            queue.add(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if("" != null){}

                }
            }
        });


    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



}
