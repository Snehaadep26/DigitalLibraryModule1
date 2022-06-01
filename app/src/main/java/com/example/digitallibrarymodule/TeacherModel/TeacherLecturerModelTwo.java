package com.example.digitallibrarymodule.TeacherModel;

public class TeacherLecturerModelTwo {

    String content;
    int id;
    String file;

    public TeacherLecturerModelTwo(String content, int id, String file) {
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