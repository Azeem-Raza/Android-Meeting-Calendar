package com.azeem.calendar;

import java.util.ArrayList;
import java.util.List;

public class MeetingStorage {
    private static List<Meeting> meetings = new ArrayList<>();

    // Add a new meeting
    public static void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public static boolean updateMeeting(Meeting updatedMeeting) {
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).getId().equals(updatedMeeting.getId())) {
                meetings.set(i, updatedMeeting);  // Replace old meeting with the updated one
                return true;  // Successfully updated
            }
        }
        return false;  // Meeting not found
    }

    // Get all meetings
    public static List<Meeting> getMeetings() {
        return meetings;
    }
}
