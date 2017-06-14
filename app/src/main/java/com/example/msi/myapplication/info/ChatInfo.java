package com.example.msi.myapplication.info;

public class ChatInfo {
    String time;
    String name;
    String content;
    public ChatInfo(String time, String name, String content) {
        super();
        this.time = time;
        this.name = name;

        this.content = content;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
