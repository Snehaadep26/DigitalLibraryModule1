package com.example.digitallibrarymodule.StudentApi;

public class StudentSubject2 {
    public int id;
    public String name;
    public String color;
    public String icon;

    public StudentSubject2(int id, String name, String color, String icon) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }
}
