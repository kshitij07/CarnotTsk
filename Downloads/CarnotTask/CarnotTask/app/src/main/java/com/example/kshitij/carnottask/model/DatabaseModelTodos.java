package com.example.kshitij.carnottask.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "todos_Table")
public class DatabaseModelTodos extends Model {
    @Column(name = "userId")
    public String userId;
    @Column(name = "title")
    public String title;
    @Column(name = "completed")
    public String completed;

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

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    List<DatabaseModelTodos> getDatabaseModelLink3List() {
        return new Select().from(DatabaseModelTodos.class).execute();
    }

}