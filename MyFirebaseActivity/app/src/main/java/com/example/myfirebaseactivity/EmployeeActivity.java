package com.example.myfirebaseactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        // Get an instance of the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        // Writing to database - key : message, value = hello, world.
        DatabaseReference myRef = database.getReference("employees");
        //Add an employee
        Employee emp = new Employee("Pamela", "Escober");
        myRef.push().setValue(emp);

        // list of all elments in firebase
        myRef = database.getReference().child("employees");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Employee emplo;
                int counter = 0;
                for(DataSnapshot ds : snapshot.getChildren()){
                    emplo = (Employee) ds.getValue(Employee.class);
                    Log.i("EMPLOYEEACTIVITY", counter + "Firstname: " + emplo.getFirstName() + " Lastname: " + emplo.getLastName());
                    counter += 1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }
}