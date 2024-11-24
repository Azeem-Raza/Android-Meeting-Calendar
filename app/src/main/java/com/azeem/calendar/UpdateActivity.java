package com.azeem.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText updateSearch, updateTitle, updatePlace, updateParticipants, updateDateTime;
    private Button updateButton, saveButton;

    private Meeting meetingToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meeting);

        // Initialize the views
        updateSearch = findViewById(R.id.updateSearch);
        updateButton = findViewById(R.id.updateButton);
        updateTitle = findViewById(R.id.updateTitle);
        updatePlace = findViewById(R.id.updatePlace);
        updateParticipants = findViewById(R.id.updateParticipants);
        updateDateTime = findViewById(R.id.updateDateTime);
        saveButton = findViewById(R.id.saveButton);

        // Search button click listener
        updateButton.setOnClickListener(v -> searchMeeting());

        // Save button click listener
        saveButton.setOnClickListener(v -> saveChanges());
    }

    private void searchMeeting() {
        String searchKeyword = updateSearch.getText().toString().trim();

        if (searchKeyword.isEmpty()) {
            Toast.makeText(this, "Please enter a keyword to search", Toast.LENGTH_SHORT).show();
            return;
        }

        // Find the meeting by title, date, or participant
        for (Meeting meeting : MeetingStorage.getMeetings()) {
            if (meeting.getTitle().toLowerCase().contains(searchKeyword.toLowerCase()) ||
                    meeting.getParticipants().toLowerCase().contains(searchKeyword.toLowerCase()) ||
                    meeting.getDate().contains(searchKeyword)) {

                // Found the meeting, display its details
                meetingToUpdate = meeting;
                updateTitle.setText(meeting.getTitle());
                updatePlace.setText(meeting.getPlace());
                updateParticipants.setText(meeting.getParticipants());
                updateDateTime.setText(meeting.getDate() + " " + meeting.getTime());

                Toast.makeText(this, "Meeting found, you can now update.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // If no meeting is found
        Toast.makeText(this, "No meeting found with the given search criteria.", Toast.LENGTH_SHORT).show();
    }

    private void saveChanges() {
        if (meetingToUpdate == null) {
            Toast.makeText(this, "No meeting to update.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the updated meeting details from the user input
        String updatedTitle = updateTitle.getText().toString().trim();
        String updatedPlace = updatePlace.getText().toString().trim();
        String updatedParticipants = updateParticipants.getText().toString().trim();
        String updatedDateTime = updateDateTime.getText().toString().trim();

        if (updatedTitle.isEmpty() || updatedPlace.isEmpty() || updatedParticipants.isEmpty() || updatedDateTime.isEmpty()) {
            Toast.makeText(this, "All fields must be filled.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Update the meeting details
        meetingToUpdate.setTitle(updatedTitle);
        meetingToUpdate.setPlace(updatedPlace);
        meetingToUpdate.setParticipants(updatedParticipants);
        meetingToUpdate.setDate(updatedDateTime.split(" ")[0]);  // Split date and time
        meetingToUpdate.setTime(updatedDateTime.split(" ")[1]);  // Split date and time

        // Save the updated meeting back to the storage
        boolean updated = MeetingStorage.updateMeeting(meetingToUpdate);
        if (updated) {
            Toast.makeText(this, "Meeting updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error updating meeting.", Toast.LENGTH_SHORT).show();
        }
    }
}