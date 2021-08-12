package com.example.LaboratoryExercise4;
public class Items {
    public Items(String itemcode, String itemdesc) {
        this.itemcode = itemcode; this.itemdesc = itemdesc;
    }
    private String itemcode;
    private String itemdesc;
    public String getItemcode() {
        return itemcode;
    }
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }
    public String getItemdesc() {
        return itemdesc;
    }
    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }
}