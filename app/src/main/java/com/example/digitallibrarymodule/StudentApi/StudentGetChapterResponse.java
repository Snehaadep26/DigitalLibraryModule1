package com.example.digitallibrarymodule.StudentApi;


import java.util.ArrayList;

public class StudentGetChapterResponse {
    public int chapterCount;
    public ArrayList<StudentChapter2> chapters;

    public StudentGetChapterResponse(int chapterCount, ArrayList<StudentChapter2> chapters) {
        this.chapterCount = chapterCount;
        this.chapters = chapters;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public ArrayList<StudentChapter2> getChapters() {
        return chapters;
    }
}

