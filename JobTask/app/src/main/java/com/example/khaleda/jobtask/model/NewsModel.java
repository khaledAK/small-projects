package com.example.khaleda.jobtask.model;

/**
 * Created by khaleda on 02/07/17.
 */

/// model news model
public class NewsModel {
    private String title;
    private String body;

    public NewsModel(String title, String body) {
        this.title = title;
        this.body = body;
    }


    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }


}
