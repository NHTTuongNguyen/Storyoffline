package com.example.projectappdoctruyen.Model;

import java.io.Serializable;

public class Chapter implements Serializable  {
    public int idChapter;
    public int idStory;
    public String title;
    public String detail;

    public Chapter(){
    }

    public Chapter(int idChapter) {
        this.idChapter = idChapter;
    }
    public Chapter(int idStory ,int idChapter, String title){
        this.idStory = idStory;
        this.idChapter =idChapter;
        this.title = title;
    }

    public Chapter(String title,int idStory) {
        this.title = title;
        this.idStory = idStory;
    }

    public Chapter(int idChapter, int idStory, String title, String detail) {
        this.idChapter = idChapter;
        this.idStory = idStory;
        this.title = title;
        this.detail = detail;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
