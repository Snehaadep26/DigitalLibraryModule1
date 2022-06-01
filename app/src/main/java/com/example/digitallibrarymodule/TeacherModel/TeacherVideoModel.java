package com.example.digitallibrarymodule.TeacherModel;

public class TeacherVideoModel {
    int icon;
    String topicName, chapterName, subjectName, link, title, published, file;

    public TeacherVideoModel(int icon, String topicName, String chapterName, String subjectName, String link, String title, String published, String file) {
        this.icon = icon;
        this.topicName = topicName;
        this.chapterName = chapterName;
        this.subjectName = subjectName;
        this.link = link;
        this.title = title;
        this.published = published;
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

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getPublished() {
        return published;
    }

    public String getFile() {
        return file;
    }
}