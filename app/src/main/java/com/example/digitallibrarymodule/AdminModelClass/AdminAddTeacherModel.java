package com.example.digitallibrarymodule.AdminModelClass;

public class AdminAddTeacherModel {
    String teacherName;
    int cancel;
    int id;

    public AdminAddTeacherModel(String teacherName, int cancel, int id) {
        this.teacherName = teacherName;
        this.cancel = cancel;
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getCancel() {
        return cancel;
    }

    public int getId() {
        return id;
    }
}
