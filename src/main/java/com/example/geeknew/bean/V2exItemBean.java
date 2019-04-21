package com.example.geeknew.bean;

import java.util.Collection;

public class V2exItemBean  {
    public String img;
    public String title;
    public String job;
    public String lastName;

    public V2exItemBean(String img, String title, String job, String lastName) {
        this.img = img;
        this.title = title;
        this.job = job;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "V2exItemBean{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", job='" + job + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
