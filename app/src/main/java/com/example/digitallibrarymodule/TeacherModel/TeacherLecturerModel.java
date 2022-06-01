package com.example.digitallibrarymodule.TeacherModel;

public class TeacherLecturerModel {
    int icon;
    String topicName, chapterName, subjectName, file;

    public TeacherLecturerModel(int icon, String topicName, String chapterName, String subjectName, String file) {
        this.icon = icon;
        this.topicName = topicName;
        this.chapterName = chapterName;
        this.subjectName = subjectName;
        this.file = file;
    }

    public int getIcon() {
        return icon;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getFile() {
        return file;
    }
}

