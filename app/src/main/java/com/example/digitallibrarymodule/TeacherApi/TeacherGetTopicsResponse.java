package com.example.digitallibrarymodule.TeacherApi;

public class TeacherGetTopicsResponse {
    public int topicId;
    public String topicName;
    public String notesCount;
    public String videoCount;
    public String quesBankCount;

    public int getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getNotesCount() {
        return notesCount;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public String getQuesBankCount() {
        return quesBankCount;
    }
}
