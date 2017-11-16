package com.example.kshitij.carnottask.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "comments_table")
public class DatabaseModelComments extends Model {
    public String getPostID() {
        return postID;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPostID(String postID) {
        this.postID = postID;

    }

    @Column(name = "postID")
    public String postID;
    @Column(name = "name")
    public String name;
    @Column(name = "email")
    public String email;
    @Column(name = "body")
    public String body;

    public static List<DatabaseModelComments> getDatabaseModelLink1List() {
        return new Select().from(DatabaseModelComments.class).execute();
    }
}