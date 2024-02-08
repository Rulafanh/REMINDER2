package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mCreateRem;
    RecyclerView mRecyclerview;
    ArrayList<Model> dataholder = new ArrayList<>(); // Simplified initialization

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this)); // Use 'this' for context
        mCreateRem = findViewById(R.id.create_reminder);

        // Floating action button to change activity
        mCreateRem.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReminderActivity.class);
            startActivity(intent); // Starts the new activity to add Reminders
        });

        loadReminders();
    }

    private void loadReminders() {
        dbManager dbManager = new dbManager(this);
        try (Cursor cursor = dbManager.readallreminders()) { // Ensures cursor is closed after use
            while (cursor.moveToNext()) {
                Model model = new Model(cursor.getString(1), cursor.getString(2), cursor.getString(3));
                dataholder.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
        }

        // Set adapter after loading data
        myAdapter adapter = new myAdapter(dataholder);
        mRecyclerview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish(); // Makes the user to exit from the app
        super.onBackPressed();
    }
}
