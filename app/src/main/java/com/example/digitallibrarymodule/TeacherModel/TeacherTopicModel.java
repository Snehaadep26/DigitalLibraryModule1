package com.example.digitallibrarymodule.TeacherModel;

public class TeacherTopicModel {
    private int ncount, v, qcount;
    private String courseDescription, topicId;

    public TeacherTopicModel(int ncount, int v, int qcount, String courseDescription, String topicId) {
        this.ncount = ncount;
        this.v = v;
        this.qcount = qcount;
        this.courseDescription = courseDescription;
        this.topicId = topicId;
    }

    public int getNcount() {
        return ncount;
    }

    public int getV() {
        return v;
    }

    public int getQcount() {
        return qcount;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getTopicId() {
        return topicId;
    }
}