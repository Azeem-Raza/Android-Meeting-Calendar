
package com.azeem.calendar;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayMeetingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MeetingAdapter meetingAdapter;
    private List<Meeting> meetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_meeting);

        recyclerView = findViewById(R.id.recyclerViewMeetings);

        // Retrieve list of meetings from MeetingStorage
        meetings = MeetingStorage.getMeetings();

        // Set up RecyclerView to display meetings using MeetingAdapter
        meetingAdapter = new MeetingAdapter(meetings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(meetingAdapter);

        // If no meetings are present, display a Toast message
        if (meetings.isEmpty()) {
            Toast.makeText(this, "No meetings to display", Toast.LENGTH_SHORT).show();
        }
    }
}
