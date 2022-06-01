package com.example.digitallibrarymodule.StudentModels;

public class StudentOverallStatsModel {
    int imageForCard;
    String zeroText, subjectText;

    public StudentOverallStatsModel(int imageForCard, String zeroText, String subjectText) {
        this.imageForCard = imageForCard;
        this.zeroText = zeroText;
        this.subjectText = subjectText;

    }

    public int getImageForCard() {
        return imageForCard;
    }

    public String getZeroText() {
        return zeroText;
    }

    public String getSubjectText() {
        return subjectText;
    }


}



