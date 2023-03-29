package com.sofkau.models;

public class PostModel {

    private int id;
    private int albumId;
    private String url;
    private String title;

    private String thumbnailUrl;



    public PostModel(int id, int albumId, String url, String title, String thumbnailUrl) {
        this.id = id;
        this.albumId = albumId;
        this.url = url;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }
    public PostModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
