package com.example.digitallibrarymodule.StudentModels;

public class StudentLecturerModel {
    String icon;
    String topicName, chapterName, subjectName, file;

    public StudentLecturerModel(String icon, String topicName, String chapterName, String subjectName, String file) {
        this.icon = icon;
        this.topicName = topicName;
        this.chapterName = chapterName;
        this.subjectName = subjectName;
        this.file = file;
    }

    public String getIcon() {
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

