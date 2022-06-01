package com.example.digitallibrarymodule.StudentApi;

import java.util.Date;

public class Content {
    public int id;
    public String title;
    public String link;
    public String file;
    public String fileName;
    public int size;
    public String type;
    public String status;
    public String publishDate;
    public String chapterName;
    public int chapterId;
    public int subjectId;
    public int standardId;
    public int topicId;
    public Date updatedAt;
    public Date createdAt;
    public StudentStandard studentStandard;
    public Topic topic;
    public Chapter chapter;
    public Subject subject;
}
