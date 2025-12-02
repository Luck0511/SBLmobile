package com.itlvck.sblmobile.dto;


import com.google.gson.annotations.SerializedName;

public class BookItem {

    @SerializedName("genre")
    private String genre;
    @SerializedName("title")
    private String title;

    @SerializedName("author")
    private String author;
    @SerializedName("description")
    private String description;
    @SerializedName("publication_year")
    private String publication_year;

    @SerializedName("isbn")
    private String isbn;

    @SerializedName("cover_Url")
    private String coverImageUrl;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }
}
