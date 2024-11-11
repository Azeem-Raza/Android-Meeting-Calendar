package com.azeem.calendar;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayMeetingActivity extends AppCompatActivity {
    private TextView tvMeetingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_meeting);

        tvMeetingDetails = findViewById(R.id.tvMeetingDetails);

        // Get meeting data from intent and display it
        Meeting meeting = (Meeting) getIntent().getSerializableExtra("meeting");
        if (meeting != null) {
            String details = "Title: " + meeting.getTitle() + "\nPlace: " + meeting.getPlace() +
                    "\nParticipants: " + meeting.getParticipants() + "\nDate: " + meeting.getDate() +
                    "\nTime: " + meeting.getTime();
            tvMeetingDetails.setText(details);
        }
    }
}
