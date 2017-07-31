package com.lefer.demo4doc.model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author fang
 * @creatdate 17-7-28
 */
public class Article {
    @JsonView(View.Summary.class)
    private String id;
    @JsonView(View.Summary2.class)
    private String title;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

