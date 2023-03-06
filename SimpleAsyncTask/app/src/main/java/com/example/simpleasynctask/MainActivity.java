package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        SimpleAsyncTask sat = new SimpleAsyncTask(mTextView);
        sat.execute();
        //new SimpleAsyncTask(mTextView).execute();
        //The execute() method is where you pass comma-separated parameters that are then passed into doInBackground() by the system.
        // Because this AsyncTask has no parameters, you leave it blank.
    }
}