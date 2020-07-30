package com.codeup.blog.models;

public class Contact {

    private long id;
    private String name;
    private String email;
    private String comments;
    private String blog;

    public Contact() {
    }

    public Contact(String name, String email, String comments) {
        this.name = name;
        this.email = email;
        this.comments = comments;
    }

    public Contact(long id, String name, String email, String comments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.comments = comments;
    }

    public Contact(String name, String email, String comments, String blog) {
        this.name = name;
        this.email = email;
        this.comments = comments;
        this.blog = blog;
    }

    public Contact(long id, String name, String email, String comments, String blog) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.comments = comments;
        this.blog = blog;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }
}


