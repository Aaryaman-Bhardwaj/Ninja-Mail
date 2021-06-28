package com.aaryaman.ninjamail.model;


public class Content
{
    String type;
    String value;

    public Content(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Content() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}