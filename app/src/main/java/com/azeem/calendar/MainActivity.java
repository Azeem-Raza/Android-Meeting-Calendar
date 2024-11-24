package com.azeem.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etPlace, etParticipants;
    private Button btnPickDate, btnPickTime, btnSubmit, btnSearch, btnSummary, btnUpdate;
    private String date = "", time = "";
    private Meeting meeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etTitle = findViewById(R.id.etTitle);
        etPlace = findViewById(R.id.etPlace);
        etParticipants = findViewById(R.id.etParticipants);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickTime = findViewById(R.id.btnPickTime);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSummary = findViewById(R.id.btnSummary);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSearch = findViewById(R.id.btnSearch);


        // Date and Time Pickers
        btnPickDate.setOnClickListener(view -> showDatePickerDialog());
        btnPickTime.setOnClickListener(view -> showTimePickerDialog());

        // Submit Button
        btnSubmit.setOnClickListener(view -> submitMeeting());

        // Navigation buttons for Search and Summary
        btnSearch.setOnClickListener(view -> openSearchActivity());
        btnSummary.setOnClickListener(view -> openDisplayMeetingActivity());
        btnUpdate.setOnClickListener(view -> openUpdateActivity());

    }

    // Open Search Activity
    private void openSearchActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    // Show Date Picker Dialog and set the date
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    btnPickDate.setText("Date: " + date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // Show Time Picker Dialog and set the time
    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (TimePicker view, int hourOfDay, int minute) -> {
                    time = hourOfDay + ":" + (minute < 10 ? "0" + minute : minute);
                    btnPickTime.setText("Time: " + time);
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    // Handle submission of meeting details
    private void submitMeeting() {
        String title = etTitle.getText().toString();
        String place = etPlace.getText().toString();
        String participants = etParticipants.getText().toString();

        // Ensure all fields are filled in
        if (title.isEmpty() || place.isEmpty() || participants.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Meeting object and add it to storage
        meeting = new Meeting(title, place, participants, date, time);
        MeetingStorage.addMeeting(meeting);
        Toast.makeText(this, "Data Submitted", Toast.LENGTH_SHORT).show();
    }

    // Open DisplayMeetingActivity to show all meetings
    private void openDisplayMeetingActivity() {
        List<Meeting> allMeetings = MeetingStorage.getMeetings();
        if (allMeetings.isEmpty()) {
            Toast.makeText(this, "No meetings to display", Toast.LENGTH_SHORT).show();
            return;
        }

        // Pass meetings data to DisplayMeetingActivity
        Intent intent = new Intent(MainActivity.this, DisplayMeetingActivity.class);
        intent.putExtra("meetings", (ArrayList<Meeting>) allMeetings);
        startActivity(intent);
    }

    // Open UpdateActivity to update a meeting
    private void openUpdateActivity() {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        startActivity(intent);
    }
}