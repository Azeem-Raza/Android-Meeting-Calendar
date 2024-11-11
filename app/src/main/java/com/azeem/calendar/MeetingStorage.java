package com.azeem.calendar;


import java.util.ArrayList;
import java.util.List;

public class MeetingStorage {
    private static List<Meeting> meetings = new ArrayList<>();

    // Add a new meeting
    public static void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    // Get all meetings
    public static List<Meeting> getMeetings() {
        return meetings;
    }
}
