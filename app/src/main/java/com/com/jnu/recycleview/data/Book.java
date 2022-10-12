package com.com.jnu.recycleview.data;

public class Book {
    public Book(String title,int coverResourceId) {
        Title = title;
        CoverResourceId = coverResourceId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getCoverResourceId() {
        return CoverResourceId;
    }

    public void setCoverResourceId(int coverResourceId) {
        CoverResourceId = coverResourceId;
    }

    private String Title;
    private int CoverResourceId;
}
