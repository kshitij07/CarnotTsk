package com.example.kshitij.carnottask.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "posts_table")
public class DatabaseModelPosts extends Model {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    @Column(name = "userId")

    public String userId;

    @Column(name = "title")
    public String title;
    @Column(name = "body")
    public String body;

    List<DatabaseModelPosts> getDatabaseModelLink4List() {
        return new Select().from(DatabaseModelPosts.class).execute();
    }
}