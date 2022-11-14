package com.example.noteapplication.modules;

import io.realm.RealmObject;

public class Notes extends RealmObject {

    String title;
    String desc;
    long time;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String description) {
        this.desc = description;
    }
}
