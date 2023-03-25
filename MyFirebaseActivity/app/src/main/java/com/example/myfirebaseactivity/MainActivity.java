package com.example.myfirebaseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myfirebaseactivity.databinding.ActivityEmployeeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main Activity";
    private ActivityEmployeeBinding binding;

    String[] employeeList = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //btn.findViewById(R.id.button);
        // Write a message to the database
        // Get an instance of the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Writing to database - key : message, value = hello, world.
        DatabaseReference myRef = database.getReference("employees");

        //myRef.setValue("Hello World");



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
                    Log.i("EMPLOYEEACTIVITY", counter + "Firstname: " + emplo.getFirstName() + " Lastname: " + emplo.getLastName());
                    employeeList[counter] = "Firstname: ";
                    System.out.println(employeeList[counter]);
                    counter += 1;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println("The read failed: " + error.getCode());
            }
        });
        // adapter
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main,employeeList);
        //binding.listaView.setAdapter(adapter);
    }

    public void employeeActivity(View view) {
        Intent intent = new Intent(this, EmployeeActivity.class);
        startActivity(intent);
    }
}