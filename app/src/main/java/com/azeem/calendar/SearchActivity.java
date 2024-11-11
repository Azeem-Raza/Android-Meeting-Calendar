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

    }

}

