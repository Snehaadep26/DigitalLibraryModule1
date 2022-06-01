package com.example.digitallibrarymodule.StudentModels;

public class StudentLecturerModelTwo {

    String content;
    int id;
    String file;

    public StudentLecturerModelTwo(String content, int id, String file) {
        this.content = content;
        this.id = id;
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }
}