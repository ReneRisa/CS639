package com.example.buttonchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "com.example.android.buttonchallenge.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSecondActivityTextThree(View view) {
        Log.d(LOG_TAG, "Text Three button");
        Intent intent = new Intent(this, SecondActivity.class);
        String textThree = getString(R.string.textViewThree);
        intent.putExtra(EXTRA_MESSAGE, textThree);
        startActivity(intent);
    }

    public void launchSecondActivityTextTwo(View view) {
        Log.d(LOG_TAG, "Text Two button");
        Intent intent = new Intent(this, SecondActivity.class);
        String textTwo = getString(R.string.textViewTwo);
        intent.putExtra(EXTRA_MESSAGE, textTwo);
        startActivity(intent);
    }

    public void launchSecondActivityTextOne(View view) {
        Log.d(LOG_TAG, "Text One button");
        Intent intent = new Intent(this, SecondActivity.class);
        String textOne = getString(R.string.textViewOne);
        intent.putExtra(EXTRA_MESSAGE, textOne);
        startActivity(intent);
    }
}