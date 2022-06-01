package com.example.digitallibrarymodule.TeacherApi;

import java.util.ArrayList;

public class TeacherGetTeacherResponse {
    public ArrayList<TeacherDatum2> data;
    public int lastWeekLectureNotesCount;
    public int lastWeekVideoCount;
    public int lastWeekQuestionBankCount;
    public ArrayList<TeacherSubject2> subjects;
    public ArrayList<TeacherAnalyticsData> analyticsData;
    public ArrayList<TeacherActiveTime> teacherActiveTime;

    public ArrayList<TeacherDatum2> getData() {
        return data;
    }

    public int getLastWeekLectureNotesCount() {
        return lastWeekLectureNotesCount;
    }

    public int getLastWeekVideoCount() {
        return lastWeekVideoCount;
    }

    public int getLastWeekQuestionBankCount() {
        return lastWeekQuestionBankCount;
    }

    public ArrayList<TeacherSubject2> getSubjects() {
        return subjects;
    }

    public ArrayList<TeacherAnalyticsData> getAnalyticsData() {
        return analyticsData;
    }

    public ArrayList<TeacherActiveTime> getActiveTime() {
        return teacherActiveTime;
    }
}
