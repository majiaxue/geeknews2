package com.example.geeknew.bean;

import java.io.Serializable;

public class GoldBean implements Serializable{
   public String title;
   public boolean isChecked;

    public GoldBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
