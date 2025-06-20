package com.cuevas.victor.literalura.dto;

import java.util.List;

public class BookDTO {
    private String title;
    private List<AuthorDTO> authors;
    private List<String> languages;
    private Integer download_count;

    public String getTitle() {
        return title;
    }

//    public void setTitle(String title) {
//        this.title = title;
//    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

//    public void setAuthors(List<AuthorDTO> authors) {
//        this.authors = authors;
//    }

    public List<String> getLanguages() {
        return languages;
    }

//    public void setLanguages(List<String> languages) {
//        this.languages = languages;
//    }

    public Integer getDownload_count() {
        return download_count;
    }

//    public void setDownload_count(Integer download_count) {
//        this.download_count = download_count;
//    }
}