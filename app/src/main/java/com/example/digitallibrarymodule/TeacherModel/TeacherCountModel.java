package com.example.digitallibrarymodule.TeacherModel;

public class TeacherCountModel {
        int imageForCard;
        String zeroText,subjectText,lastWeek;

        public TeacherCountModel(int imageForCard, String zeroText, String subjectText, String lastWeek) {
            this.imageForCard = imageForCard;
            this.zeroText = zeroText;
            this.subjectText = subjectText;
            this.lastWeek=lastWeek;
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

    public String getLastWeek() {
        return lastWeek;
    }
}



