package com.itlvck.sblmobile.dto;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookItem {
    //variables
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

    @SerializedName("cover_url")
    private String coverImageUrl;

    //getters
    public String getGenre() {
        return genre;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getDescription() {
        return description;
    }
    public String getPublication_year() {
        return publication_year;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    //setters
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    //For the Research Fragment
    public class ResearchResponse {
        private List<BookItem> books;

        public List<BookItem> getBooks() {
            return books;
        }
        public void setBooks(List<BookItem> books) {
            this.books = books;
        }
    }
}

