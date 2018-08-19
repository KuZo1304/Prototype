package com.example.kuzo.prototype;

import com.android.volley.toolbox.StringRequest;

public class Item {

    private String title;
    private String desc;


    public Item(String title) {
        super();
        this.title = title;

    }

    public Item(String title,String desc) {
        super();
        this.title = title;
        this.desc=desc;

    }

    // getters and setters...

    public String getTitle() {
        return title;
    }

    public String getDesc(){
        return desc;
    }

}