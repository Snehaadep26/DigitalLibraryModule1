package com.example.digitallibrarymodule.TeacherApi;

import java.util.ArrayList;

public class TeacherChapterListResponse {
    public int chapterCount;
    public ArrayList<TeacherChapter2> chapters;

    public int getChapterCount() {
        return chapterCount;
    }

    public ArrayList<TeacherChapter2> getChapters() {
        return chapters;
    }
}
