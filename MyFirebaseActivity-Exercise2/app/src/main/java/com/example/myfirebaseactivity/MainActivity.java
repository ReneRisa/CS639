package com.example.myfirebaseactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myfirebaseactivity.databinding.ActivityEmployeeBinding;
import com.example.myfirebaseactivity.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main Activity";
    public LinkedList<String> mWordList = new LinkedList<>();
    private ActivityMainBinding binding;

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //btn.findViewById(R.id.button);
        // Write a message to the database
        // Get an instance of the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Writing to database - key : message, value = hello, world.
        DatabaseReference myRef = database.getReference("employees");

        //myRef.setValue("Hello World");


        DatabaseReference finalMyRef = myRef;
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWordList.clear();
                Employee emp = new Employee(binding.lName.getText().toString(), binding.fName.getText().toString());
                finalMyRef.push().setValue(emp);
            }
        });
        // Read from the database
        // list of all elements in firebase
        myRef = database.getReference().child("employees");
        // Anonymous class
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                /*Log.d(TAG,"String.class: " + String.class);
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);*/
                Employee emplo;
                int counter = 0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    emplo = (Employee) ds.getValue(Employee.class);
                    Log.i(TAG, counter + "Firstname: " + emplo.getFirstName() + " Lastname: " + emplo.getLastName());
                    mWordList.addLast( emplo.getFirstName() + "  " + emplo.getLastName());
                    counter += 1;
                }
                // Get a handle to the RecyclerView.
                mRecyclerView = binding.recyclerview;
                // Create an adapter and supply the data to be displayed.
                mAdapter = new ListAdapter(MainActivity.this, mWordList);
// Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
// Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println("The read failed: " + error.getCode());
            }


        });

    }

    public void employeeActivity(View view) {
        //Intent intent = new Intent(this, EmployeeActivity.class);
        //startActivity(intent);
        //Add an employee

    }

}