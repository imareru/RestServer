package com.example.demo.entity;

public class Film extends BaseEntity{
    private String film_name;
    private String genre;
    private Double price;
    private String ageLimit;
    private String description;

    public Film(int id, String film_name, String genre, double price, String ageLimit, String description) {
        super(id);
        this.film_name = film_name;
        this.genre = genre;
        this.price = price;
        this.ageLimit = ageLimit;
        this.description = description;
    }


    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
