package com.azeem.calendar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class SearchActivity extends AppCompatActivity {
    private EditText etSearchDate, etSearchTime, etSearchParticipants;
    private Button btnSearch;
    private RecyclerView recyclerViewResults;
    private MeetingAdapter meetingAdapter;  // Adapter to display meetings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearchDate = findViewById(R.id.etSearchDate);
        etSearchTime = findViewById(R.id.etSearchTime);
        etSearchParticipants = findViewById(R.id.etSearchParticipants);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerViewResults = findViewById(R.id.recyclerViewResults);

        // Set up RecyclerView
        recyclerViewResults.setLayoutManager(new LinearLayoutManager(this));
        meetingAdapter = new MeetingAdapter(new ArrayList<>());  // Initialize with empty list
        recyclerViewResults.setAdapter(meetingAdapter);

        // Set up Search button
        btnSearch.setOnClickListener(view -> searchMeetings());
    }

    private void searchMeetings() {
        String dateInput = etSearchDate.getText().toString().trim();
        String timeInput = etSearchTime.getText().toString().trim();
        String participantInput = etSearchParticipants.getText().toString().trim();

        // Log the user inputs
        Log.d("SearchActivity", "Searching with: Date=" + dateInput + ", Time=" + timeInput + ", Participant=" + participantInput);

        // Create a SimpleDateFormat for consistent date formatting
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // input format (user input)
        SimpleDateFormat meetingDateFormat = new SimpleDateFormat("MM/dd/yyyy"); // meeting stored format

        List<Meeting> filteredMeetings = new ArrayList<>();

        for (Meeting meeting : MeetingStorage.getMeetings()) {
            Log.d("SearchActivity", "Checking meeting: " + meeting.getTitle() + ", Date=" + meeting.getDate() + ", Time=" + meeting.getTime() + ", Participants=" + meeting.getParticipants());

            // Convert input date and meeting date to the same format for comparison
            boolean matchesDate = false;
            if (!TextUtils.isEmpty(dateInput)) {
                try {
                    Date parsedInputDate = inputDateFormat.parse(dateInput); // Parse user input date
                    Date parsedMeetingDate = meetingDateFormat.parse(meeting.getDate()); // Parse stored meeting date
                    matchesDate = parsedInputDate != null && parsedMeetingDate != null && parsedInputDate.equals(parsedMeetingDate);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle exception if date format is wrong
                }
            } else {
                matchesDate = true; // If date input is empty, skip date comparison
            }

            // Time comparison (make sure the input format matches)
            boolean matchesTime = TextUtils.isEmpty(timeInput) || timeInput.equals(meeting.getTime());

            // Participant comparison (case-insensitive matching)
            boolean matchesParticipant = TextUtils.isEmpty(participantInput) || meeting.getParticipants().toLowerCase().contains(participantInput.toLowerCase());

            Log.d("SearchActivity", "Matches Date: " + matchesDate + ", Matches Time: " + matchesTime + ", Matches Participant: " + matchesParticipant);

            // Add the meeting to the filtered list if all conditions match
            if (matchesDate && matchesTime && matchesParticipant) {
                filteredMeetings.add(meeting);
            }
        }

        Log.d("SearchActivity", "Filtered meetings: " + filteredMeetings.size());

        if (filteredMeetings.isEmpty()) {
            // Optionally, show a message that no meetings match
            Toast.makeText(this, "No meetings found matching your criteria.", Toast.LENGTH_SHORT).show();
        }

        // Update the adapter with the filtered list of meetings
        meetingAdapter.updateMeetings(filteredMeetings);
    }

}

