package com.example.digitallibrarymodule.TeacherApi;

import java.util.ArrayList;


public class TeacherGetLibraryResponse {
        public ArrayList<TotalCount> totalCount;
        public int lastWeekLectureNotesCount;
        public int lastWeekVideoCount;
        public int lastWeekQuestionBankCount;
        public ArrayList<Content> contents;

    public ArrayList<TotalCount> getTotalCount() {
        return totalCount;
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

    public ArrayList<Content> getContents() {
        return contents;
    }
}



