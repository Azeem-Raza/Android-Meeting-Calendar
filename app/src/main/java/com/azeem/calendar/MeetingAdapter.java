package com.azeem.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {
    private List<Meeting> meetings;
    private OnItemClickListener onItemClickListener;

    public MeetingAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    // Interface for handling item clicks
    public interface OnItemClickListener {
        void onItemClick(Meeting meeting);
    }

    // Set the item click listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Update the meetings list and refresh the RecyclerView
    public void updateMeetings(List<Meeting> newMeetings) {
        meetings = newMeetings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);

        // Set the title in the first TextView
        holder.title.setText("Title: " + meeting.getTitle());

        // Set the rest of the information in the second TextView (place, participants, date, and time)
        String meetingDetails = "Place: " + meeting.getPlace() + "\n" +
                "Participants: " + meeting.getParticipants() + "\n" +
                "Date: " + meeting.getDate() + "\n" +
                "Time: " + meeting.getTime();
        holder.place.setText(meetingDetails);

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(meeting);
            }
        });
    }


    @Override
    public int getItemCount() {
        return meetings.size();
    }

    // ViewHolder class for binding the meeting data to the views
    public static class MeetingViewHolder extends RecyclerView.ViewHolder {
        TextView title, place, participants, date, time;

        public MeetingViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            place = itemView.findViewById(android.R.id.text2);



        }
    }
}
