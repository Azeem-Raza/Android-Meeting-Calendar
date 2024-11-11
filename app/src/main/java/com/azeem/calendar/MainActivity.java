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
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etPlace, etParticipants;
    private Button btnPickDate, btnPickTime, btnSubmit, btnSearch;
    private String date = "", time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etPlace = findViewById(R.id.etPlace);
        etParticipants = findViewById(R.id.etParticipants);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnPickTime = findViewById(R.id.btnPickTime);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnPickDate.setOnClickListener(view -> showDatePickerDialog());
        btnPickTime.setOnClickListener(view -> showTimePickerDialog());

        btnSubmit.setOnClickListener(view -> submitMeeting());
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> openSearchActivity());


        // Set up buttons for Summary, Search, and Update to start respective activities


    }
    private void openSearchActivity() {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }


    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    btnPickDate.setText("Date: " + date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (TimePicker view, int hourOfDay, int minute) -> {
                    time = hourOfDay + ":" + (minute < 10 ? "0" + minute : minute);
                    btnPickTime.setText("Time: " + time);
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void submitMeeting() {
        String title = etTitle.getText().toString();
        String place = etPlace.getText().toString();
        String participants = etParticipants.getText().toString();

        Meeting meeting = new Meeting(title, place, participants, date, time);

        // Start DisplayMeetingActivity with meeting details
        Intent intent = new Intent(MainActivity.this, DisplayMeetingActivity.class);
        intent.putExtra("meeting", meeting);
        startActivity(intent);
        // Add the meeting to storage
        MeetingStorage.addMeeting(meeting);

    }
}
