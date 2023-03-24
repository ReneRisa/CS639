package com.example.shoppinglist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.shoppinglist.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    private ActivityMainBinding binding;

    public int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String item = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                switch (i){
                    case 1:
                        binding.textView2.setText(item);
                        i++;
                        break;
                    case 2:
                        binding.textView3.setText(item);
                        i++;
                        break;
                    case 3:
                        binding.textView4.setText(item);
                        i++;
                        break;
                }
                //int i = 1;
                //while(i < 11){
                    //if(binding.textView2.getText().toString() == ""){

                    //}
                    //i++;
                //}
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