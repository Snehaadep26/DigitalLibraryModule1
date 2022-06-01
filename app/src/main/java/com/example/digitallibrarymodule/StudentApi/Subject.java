package com.example.digitallibrarymodule.StudentApi;

public class Subject {
    public int subjects_id;
    public String subjects_name;
    public String icon;
    public int chapterCount;
    public String notesCount;
    public String videoCount;
    public String quesBankCount;

    public Subject(int subjects_id, String subjects_name, String icon, int chapterCount, String notesCount, String videoCount, String quesBankCount) {
        this.subjects_id = subjects_id;
        this.subjects_name = subjects_name;
        this.icon = icon;
        this.chapterCount = chapterCount;
        this.notesCount = notesCount;
        this.videoCount = videoCount;
        this.quesBankCount = quesBankCount;
    }

    public int getSubjects_id() {
        return subjects_id;
    }

    public String getSubjects_name() {
        return subjects_name;
    }

    public String getIcon() {
        return icon;
    }

    public int getChapterCount() {
        return chapterCount;
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
