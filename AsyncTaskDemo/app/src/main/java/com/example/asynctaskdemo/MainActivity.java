package com.example.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Call of AsyncTask class
        // Creation of the AsyncTask
        // Execution with a random image from picsum.photos
        AsyncTaskImgDownloader imgDownloader = new AsyncTaskImgDownloader(this);
        // Call without a file type
        imgDownloader.execute("https://picsum.photos/300", "https://picsum.photos/id/237/200/300");
        // Call with a file type
        // imgDownloader.execute("https://picsum.photos/200/300.jpg");
    }
}