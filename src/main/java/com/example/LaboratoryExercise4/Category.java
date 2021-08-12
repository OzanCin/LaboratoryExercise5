package com.example.LaboratoryExercise4;

public class Category {

    private String catcode;
    private String catdesc;

    public Category(String catcode, String catdesc) {
        this.catcode = catcode;
        this.catdesc = catdesc;
    }

    public String getCatcode() {
        return catcode;
    }

    public void setCatcode(String catcode) {
        this.catcode = catcode;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }
}
