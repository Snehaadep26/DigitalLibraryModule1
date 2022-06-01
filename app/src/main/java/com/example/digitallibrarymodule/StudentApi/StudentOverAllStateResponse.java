package com.example.digitallibrarymodule.StudentApi;

import java.util.ArrayList;

public class StudentOverAllStateResponse {
    public boolean showLibrary;
    public ArrayList<TotalCount> totalCount;
    public int lastWeekLectureNotesCount;
    public int lastWeekVideoCount;
    public int lastWeekQuestionBankCount;
    public ArrayList<Subject> subjects;
    public ArrayList<QuestionBank> questionBank;
    public ArrayList<Video> video;
    public ArrayList<LectureNote> lectureNotes;

}


