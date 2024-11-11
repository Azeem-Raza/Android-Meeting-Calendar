package com.azeem.calendar;
import java.io.Serializable;

public class Meeting implements Serializable {
    private String title;
    private String place;
    private String participants;
    private String date;
    private String time;

    // Constructor
    public Meeting(String title, String place, String participants, String date, String time) {
        this.title = title;
        this.place = place;
        this.participants = participants;
        this.date = date;
        this.time = time;
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getParticipants() { return participants; }
    public void setParticipants(String participants) { this.participants = participants; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}
