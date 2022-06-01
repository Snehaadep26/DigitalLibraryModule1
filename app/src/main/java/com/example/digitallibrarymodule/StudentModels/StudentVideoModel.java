package com.example.digitallibrarymodule.StudentModels;

public class StudentVideoModel {
    String icon;
    String topicName, chapterName, subjectName, link, title, published, file;

    public StudentVideoModel(String icon, String topicName, String chapterName, String subjectName, String link, String title, String published, String file) {
        this.icon = icon;
        this.topicName = topicName;
        this.chapterName = chapterName;
        this.subjectName = subjectName;
        this.link = link;
        this.title = title;
        this.published = published;
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