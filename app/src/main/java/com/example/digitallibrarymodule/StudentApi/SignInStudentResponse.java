package com.example.digitallibrarymodule.StudentApi;

import java.util.ArrayList;
import java.util.Date;

public class SignInStudentResponse {
    public Show show;
    public Data data;

    public SignInStudentResponse(Show show, Data data) {
        this.show = show;
        this.data = data;
    }

    public Show getShow() {
        return show;
    }

    public Data getData() {
        return data;
    }
}

