package com.example.myfirstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myfirstapp.FirstFragmentDirections;
import com.example.myfirstapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    TextView showCountTextView;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_first, container, false);
        // Get the count text view
        showCountTextView = fragmentFirstLayout.findViewById(R.id.textview_first);
        return fragmentFirstLayout;

        /*binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();*/
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*binding.randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
        view.findViewById(R.id.random_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //line to find the count text view textview_first and parse it
                int currentCount = Integer.parseInt(showCountTextView.getText().toString());
                // action with the currentCount as the argument to actionfirsttosecondfragment()
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount);
                //to find the nav controller and navigate with the action I created
                NavHostFragment.findNavController(FirstFragment.this).navigate(action);
            }
        });

        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getActivity(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
        view.findViewById(R.id.count_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countMe(view);
            }
        });

    }
    private void countMe(View view){
        // Get the value of the text view
        String countString = showCountTextView.getText().toString();
        // Convert value to a number and increment it
        Integer count = Integer.parseInt(countString);
        count++;
        // Display the new value in the text view.
        showCountTextView.setText(count.toString());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}