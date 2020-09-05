package com.sample.blog.forms;

import javax.validation.constraints.Size;

public class PostForm {
    @Size(min=2, max=30, message = "Title size should be in the range [2...30]")
    private String title;

    @Size(max=1000, message = "You've exceeded the max body size")
    private String body;

    @Size(max=50, message= "you've exceeded the max author size!")
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
