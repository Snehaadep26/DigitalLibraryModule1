package com.example.digitallibrarymodule.StudentModels;

public class StudentContentModel
{
    int imageForCard;
        String zeroText,subjectText,lastWeek;

        public StudentContentModel(int imageForCard, String zeroText, String subjectText, String lastWeek) {
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
