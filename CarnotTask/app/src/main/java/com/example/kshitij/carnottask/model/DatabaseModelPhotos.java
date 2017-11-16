package com.example.kshitij.carnottask.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "photos_table")
public class DatabaseModelPhotos extends Model {
    @Column(name = "albumId")
    public String albumId;


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Column(name = "title")
    public String title;
    @Column(name = "url")
    public String url;
    @Column(name = "thumbnailUrl")
    public String thumbnailUrl;

    List<DatabaseModelPhotos> getDatabaseModelLink2List() {
        return new Select().from(DatabaseModelComments.class).execute();
    }
}