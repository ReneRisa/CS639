package com.example.conversionreneiriasv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.conversionreneiriasv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void currencyConvert(View view) {

        double usd = Double.parseDouble(binding.editTextUSD.getText().toString());
        if(usd != 0.00){
            double eur = usd * 0.88;
            binding.editTextEURO.setText(String.valueOf(eur));
        }

    }
}