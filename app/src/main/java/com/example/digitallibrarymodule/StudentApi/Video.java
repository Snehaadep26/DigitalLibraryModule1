package com.example.digitallibrarymodule.StudentApi;

import java.util.Date;

public class Video {
    public int id;
    public String title;
    public String link;
    public String file;
    public Object fileName;
    public int size;
    public String type;
    public String status;
    public Object publishDate;
    public String chapterName;
    public int chapterId;
    public int subjectId;
    public int standardId;
    public int topicId;
    public Date updatedAt;
    public Date createdAt;
    public StudentStandard studentStandard;
    public StudentSubject2 subject;
    public Chapter chapter;
    public Topic topic;

    public Video(int id, String title, String link, String file, Object fileName, int size, String type, String status, Object publishDate, String chapterName, int chapterId, int subjectId, int standardId, int topicId, Date updatedAt, Date createdAt, StudentStandard studentStandard, StudentSubject2 subject, Chapter chapter, Topic topic) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.file = file;
        this.fileName = fileName;
        this.size = size;
        this.type = type;
        this.status = status;
        this.publishDate = publishDate;
        this.chapterName = chapterName;
        this.chapterId = chapterId;
        this.subjectId = subjectId;
        this.standardId = standardId;
        this.topicId = topicId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.studentStandard = studentStandard;
        this.subject = subject;
        this.chapter = chapter;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getFile() {
        return file;
    }

    public Object getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Object getPublishDate() {
        return publishDate;
    }

    public String getChapterName() {
        return chapterName;
    }

    public int getChapterId() {
        return chapterId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStandardId() {
        return standardId;
    }

    public int getTopicId() {
        return topicId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public StudentStandard getStudentStandard() {
        return studentStandard;
    }

    public StudentSubject2 getSubject() {
        return subject;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public Topic getTopic() {
        return topic;
    }
}
