package com.blup.android.blup.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blup.android.blup.R;

public class ItemFocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_focus);

        TextView title = (TextView) findViewById(R.id.item_title);
        Bundle extras = getIntent().getExtras();

        String itemId = getIntent().getStringExtra("item_id");

        title.setText(itemId);
    }
}
