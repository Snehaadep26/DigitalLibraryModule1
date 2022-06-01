package com.example.digitallibrarymodule.AdminApiLibrary;

public class TotalCount {
    public String toalNotesCount;
    public String totalVideoCount;
    public String totalQuesBankCount;
    public int lastWeekVideoCount;
    public int lastWeekQuestionBankCount;
    public int lastWeekLectureNotesCount;

    public TotalCount(String toalNotesCount, String totalVideoCount, String totalQuesBankCount, int lastWeekVideoCount, int lastWeekQuestionBankCount, int lastWeekLectureNotesCount) {
        this.toalNotesCount = toalNotesCount;
        this.totalVideoCount = totalVideoCount;
        this.totalQuesBankCount = totalQuesBankCount;
        this.lastWeekVideoCount = lastWeekVideoCount;
        this.lastWeekQuestionBankCount = lastWeekQuestionBankCount;
        this.lastWeekLectureNotesCount = lastWeekLectureNotesCount;
    }

    public String getToalNotesCount() {
        return toalNotesCount;
    }

    public String getTotalVideoCount() {
        return totalVideoCount;
    }

    public String getTotalQuesBankCount() {
        return totalQuesBankCount;
    }

    public int getLastWeekVideoCount() {
        return lastWeekVideoCount;
    }

    public int getLastWeekQuestionBankCount() {
        return lastWeekQuestionBankCount;
    }

    public int getLastWeekLectureNotesCount() {
        return lastWeekLectureNotesCount;
    }
}
