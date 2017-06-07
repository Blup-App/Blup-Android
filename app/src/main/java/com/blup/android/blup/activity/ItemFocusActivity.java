package com.blup.android.blup.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.blup.android.blup.R;
import com.blup.android.blup.adaptateritem.MyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ItemFocusActivity extends AppCompatActivity {
    private TextView title;
    private TextView note;
    private ImageView preview;
    private TextView dates1;
    private TextView dates2;
    private TextView daysLeft1;
    private TextView daysLeft2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_focus);

        title = (TextView) findViewById(R.id.item_title);
        note = (TextView) findViewById(R.id.item_note);
        preview = (ImageView) findViewById(R.id.item_preview);

        dates1 = (TextView) findViewById(R.id.item_dates_1);
        dates2 = (TextView) findViewById(R.id.item_dates_2);

        daysLeft1 = (TextView) findViewById(R.id.item_days_left_1);
        daysLeft2 = (TextView) findViewById(R.id.item_days_left_2);



        String itemId = getIntent().getStringExtra("item_id");

        RequestQueue queue;
        queue = Volley.newRequestQueue(this);

        final JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, "http://192.168.1.20:8000/api/items/" + itemId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            title.setText(response.getString("title"));
                            note.setText(response.getString("note"));

                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                            String lendDateStr = response.getString("lendDate");
                            String returnDateStr = response.getString("returnDate");

                            dates1.setText("Du " + lendDateStr.substring(5, 10).replace("-", "/") + " au " + returnDateStr.substring(5, 10).replace("-", "/"));
                            dates2.setText("Du " + lendDateStr.substring(5, 10).replace("-", "/") + " au " + returnDateStr.substring(5, 10).replace("-", "/"));
                            Date lendDate = df.parse(lendDateStr, new ParsePosition(0));
                            Date returnDate = df.parse(returnDateStr, new ParsePosition(0));
                            Date now = new Date();

                            long remaining = returnDate.getTime() - now.getTime();
                            long diff = returnDate.getTime() - lendDate.getTime();

                            long percentage = (remaining * 100) / diff;

                            View progressBar1 = findViewById(R.id.login_progress_bar_1);
                            View progressBar2 = findViewById(R.id.login_progress_bar_2);

                            String days = Long.toString(TimeUnit.DAYS.convert(remaining, TimeUnit.MILLISECONDS));

                            int width = progressBar1.getWidth();
                            long progressWidth = (percentage) * (width / 100);

                            ViewGroup.LayoutParams params = progressBar2.getLayoutParams();
                            params.width = (int) (progressWidth) + 20;
                            params.height = progressBar1.getHeight();

                            progressBar2.setLayoutParams(params);

                            daysLeft1.setText(days);
                            daysLeft2.setText(days);


                            if((int) (progressWidth + 0) != 0){}

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
