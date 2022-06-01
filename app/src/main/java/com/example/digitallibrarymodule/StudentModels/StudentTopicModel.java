package com.example.digitallibrarymodule.StudentModels;

public class StudentTopicModel {
    private int vcount, qcount, ncount;
    private String courseDescription,topicId;

    public StudentTopicModel(int vcount, int qcount, int ncount, String courseDescription, String topicId) {

        this.vcount = vcount;
        this.qcount = qcount;
        this.ncount = ncount;
        this.courseDescription = courseDescription;
        this.topicId=topicId;
    }


    public int getVcount() {
        return vcount;
    }

    public void setVcount(int vcount) {
        this.vcount = vcount;
    }

    public int getQcount() {
        return qcount;
    }

    public void setQcount(int qcount) {
        this.qcount = qcount;
    }

    public int getNcount() {
        return ncount;
    }

    public void setNcount(int ncount) {
        this.ncount = ncount;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getTopicId() {
        return topicId;
    }
}
