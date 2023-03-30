package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shoppinglist.databinding.ActivitySecondBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.example.android.shoppinglist.extra.REPLY";

    private ActivitySecondBinding binding;
    private final ArrayList<String> mWordList = new ArrayList<>(Arrays.asList("Eggs", "Hummus", "Tortilla","Pasta"));
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.eggsBtn.setOnClickListener(this);
        binding.beansBtn.setOnClickListener(this);
        binding.hummusBtn.setOnClickListener(this);
        binding.tortillaBtn.setOnClickListener(this);
        binding.pastaBtn.setOnClickListener(this);


        int wordListSize = mWordList.size();
        //Get a handle to the RecyclerViewer
        mRecyclerView = binding.recyclerview;
        //create an adapter and supply the data to be displayed
        mAdapter = new WordListAdapter(this, mWordList);
        //Connect the adapter with the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView  a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Scroll to the bottom
        mRecyclerView.smoothScrollToPosition(wordListSize);
    }
    @Override
    public void onClick(View view){
        /*switch(view.getId()){
            case R.id.eggsBtn:
                Log.d(LOG_TAG, "egg btn");
                break;
            case R.id.hummusBtn:
                Log.d(LOG_TAG, "hummus btn");
                break;
            case R.id.beansBtn:
                Log.d(LOG_TAG, "beans btn");
                break;
        }*/
        Log.d(LOG_TAG, "onClick Clicked");
        Button btn = (Button) findViewById(view.getId());
        String item = btn.getText().toString();
        Log.d(LOG_TAG, item);
        returnResult(item);
    }
    public void returnResult(String item){
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, item);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}