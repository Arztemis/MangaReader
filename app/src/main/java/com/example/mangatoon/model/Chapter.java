package com.example.mangatoon.model;

public class Chapter {
    String chapter, timeUpload;

    public Chapter(String chapter, String timeUpload) {
        this.chapter = chapter;
        this.timeUpload = timeUpload;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(String timeUpload) {
        this.timeUpload = timeUpload;
    }
}
