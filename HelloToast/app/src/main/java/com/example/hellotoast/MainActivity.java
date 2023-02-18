package com.example.hellotoast;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;
import static android.graphics.Color.rgb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount;
    private TextView mShowCount;

    private Button buttonCountZero;
    private Button buttonCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //to find a view(textview in this case) in the layout by id
        mShowCount = (TextView) findViewById(R.id.show_count);
        buttonCountZero = findViewById(R.id.button_zero);
        buttonCount = findViewById(R.id.button_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        buttonCountZero.setBackgroundColor(YELLOW);
        if(mShowCount != null){
            mShowCount.setText(Integer.toString(mCount));
        }
        if(mCount%2==0){
            view.setBackgroundColor(rgb(200, 1, 20));
        }else{
            view.setBackgroundColor(GREEN);
        }
    }

    public void countZero(View view) {
        Log.i("MainActivity","pressed zero button");
        if(mShowCount != null){
            mShowCount.setText(Integer.toString(0));
            mCount = 0;
            buttonCountZero.setBackgroundColor(GRAY);
            buttonCount.setBackgroundColor(BLUE);
        }
    }
}