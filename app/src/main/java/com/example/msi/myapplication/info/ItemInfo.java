package com.example.msi.myapplication.info;

import java.util.ArrayList;
import java.util.List;

public class ItemInfo {
    String name;
    String counter;
    int img;
    public ItemInfo() {
        super();
        this.name = name;
        this.img = img;
        this.counter = counter;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCounter() {
        return counter;
    }
    public void setCounter(String counter) {
        this.counter = counter;
    }
    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }
}
