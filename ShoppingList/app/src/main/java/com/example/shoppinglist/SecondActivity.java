package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shoppinglist.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.example.android.shoppinglist.extra.REPLY";

    private ActivitySecondBinding binding;
    private Button eggBtn;
    private Button beansBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        eggBtn = findViewById(R.id.eggsBtn);
        binding.eggsBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.eggsBtn:
                Log.d(LOG_TAG, "egg btn");
            case R.id.hummusBtn:
                Log.d(LOG_TAG, "hummus btn");
            case R.id.beansBtn:
                Log.d(LOG_TAG, "beans btn");
        }
        Log.d(LOG_TAG, "onClick Clicked");
        Button btn = (Button) findViewById(view.getId());
        String item = btn.getText().toString();
        Log.d(LOG_TAG, item);
        //returnResult(item);
    }
    public void returnResult(String item){
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}