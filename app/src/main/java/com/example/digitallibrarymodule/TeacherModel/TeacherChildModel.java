package com.example.digitallibrarymodule.TeacherModel;

public class TeacherChildModel {
      private  int notesCount,videoCount,quesCount,standardId;
    private String course,section,standard;

    public TeacherChildModel(int notesCount, int videoCount, int quesCount, int standardId, String course, String section, String standard) {
        this.notesCount = notesCount;
        this.videoCount = videoCount;
        this.quesCount = quesCount;
        this.standardId=standardId;
        this.course = course;
        this.section = section;
        this.standard=standard;
    }

    public int getNotesCount() {
        return notesCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public int getQuesCount() {
        return quesCount;
    }

    public int getStandardId() {
        return standardId;
    }

    public String getCourse() {
        return course;
    }

    public String getSection() {
        return section;
    }

    public String getStandard() {
        return standard;
    }
}