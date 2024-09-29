package com.example.EDairy.model;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String description;
    private int date;
    private int semesterNumber;
    private String futureTasks;

    public Note(int id, String title, String description, int date, int semesterNumber, String futureTasks) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.semesterNumber = semesterNumber;
        this.futureTasks = futureTasks;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public String getFutureTasks() {
        return futureTasks;
    }

}
