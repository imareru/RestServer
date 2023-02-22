package com.example.demo.resource;

import com.example.demo.entity.Film;
import com.fasterxml.jackson.annotation.JsonInclude;

public class FilmResource extends BaseResource{
    private Integer id;
    private String film_name;
    private String genre;
    private double price;
    private String age_limit;
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderResource[] order;

    public FilmResource(){}

    public FilmResource(Film filmEntity){
        this.id = filmEntity.getId();
        this.film_name = filmEntity.getFilm_name();
        this.genre = filmEntity.getGenre();
        this.price = filmEntity.getPrice();
        this.age_limit = filmEntity.getAgeLimit();
        this.description = filmEntity.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String name) {
        this.film_name = film_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(String age_limit) {
        this.age_limit = age_limit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderResource[] getOrder() { return order; }

    public void setOrder(OrderResource[] order) {
        this.order = order;
    }

    public Film toEntity(){
        return new Film(
                this.id,
                this.film_name,
                this.genre,
                this.price,
                this.age_limit,
                this.description
        );
    }
}
