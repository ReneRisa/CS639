package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shoppinglist.databinding.ActivityMainBinding;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    private ActivityMainBinding binding;

    private ArrayList<String> mWordList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    public int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(savedInstanceState != null){
            boolean isSaved = savedInstanceState.getBoolean("saved");
            if(isSaved){

                mWordList = savedInstanceState.getStringArrayList("savedArray");
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

        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("saved", true);
        outState.putStringArrayList("savedArray", mWordList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String item = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                int wordListSize = mWordList.size();
                //Add a new word to the worList
                mWordList.add(item);
                Log.i(LOG_TAG, String.valueOf(mWordList));

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
        }
    }

    public void addItem(View view) {
        Log.d(LOG_TAG, "Add Item Clicked");
        Intent intent = new Intent(this, SecondActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, TEXT_REQUEST);
    }


}