package com.example.mytemplate.main.model.pojo;

public class DefaultItemList {

    private int id = -1;
    private String text = "";

    public DefaultItemList(int id, String text){
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
