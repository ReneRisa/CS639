package com.example.myfirstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.fragment.*;

import com.example.myfirstapp.databinding.FragmentSecondBinding;


import java.util.Random;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //code to get the current count
        Integer count = SecondFragmentArgs.fromBundle(getArguments()).getMyArg();
        // to get the string and format it with the count
        String countText = getString(R.string.random_heading, count);
        // set it for textview_header
        TextView headerView = view.getRootView().findViewById(R.id.textview_header);
        headerView.setText(countText);

        //Get a random number between 0 and the count
        Random random = new Random();
        Integer randomNumber = 0;
        if(count > 0){
            randomNumber = random.nextInt(count + 1);
        }

        //Code to convert that random number into a string and set it as the text for textview_random
        TextView randomView = view.getRootView().findViewById(R.id.textview_random);
        randomView.setText(randomNumber.toString());

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}