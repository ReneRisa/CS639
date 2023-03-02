package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text_message_reply);
        textView.setText(message);
        mMessageEditText = findViewById(R.id.editText_second);
    }

    public void returnReply(View view) {
        Log.d(LOG_TAG, "Reply Clicked");
        Intent replyIntent = new Intent();
        String message = mMessageEditText.getText().toString();
        replyIntent.putExtra(EXTRA_REPLY, message);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
}