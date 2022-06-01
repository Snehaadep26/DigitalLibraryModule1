package com.example.digitallibrarymodule.StudentModels;

public class StudentSubjectModel {
    String subjectName,chapters,icon;
    int ntsCount,vdoCount,quesCount,subjectId;

    public StudentSubjectModel(String subjectName, String chapters, int ntsCount, int vdoCount, int quesCount, int subjectId, String icon) {
        this.subjectName = subjectName;
        this.chapters = chapters;
        this.ntsCount = ntsCount;
        this.vdoCount = vdoCount;
        this.quesCount = quesCount;
        this.subjectId=subjectId;
        this.icon=icon;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getChapters() {
        return chapters;
    }

    public int getNtsCount() {
        return ntsCount;
    }

    public int getVdoCount() {
        return vdoCount;
    }

    public int getQuesCount() {
        return quesCount;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getIcon() {
        return icon;
    }
}
