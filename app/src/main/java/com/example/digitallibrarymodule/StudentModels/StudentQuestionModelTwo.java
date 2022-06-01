package com.example.digitallibrarymodule.StudentModels;

public class StudentQuestionModelTwo {

    String infoText,file;

    public StudentQuestionModelTwo(String infoText, String file) {

        this.infoText = infoText;
        this.file = file;
    }



    public String getInfoText() {
        return infoText;
    }

    public String getFile() {
        return file;
    }
}
