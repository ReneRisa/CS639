package com.example.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;
    private TextView mShoppingList1;
    private TextView mShoppingList2;
    private TextView mShoppingList3;
    private TextView mShoppingList4;
    private TextView mShoppingList5;
    private TextView mShoppingList6;
    private TextView mShoppingList7;
    private TextView mShoppingList8;
    private TextView mShoppingList9;
    private TextView mShoppingList10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addItem(View view) {
        Log.d(LOG_TAG, "Add Item Clicked");
        Intent intent = new Intent(this, SecondActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}