package com.example.demo.entity;

public class Room extends BaseEntity{
    private Integer numOfSeats;
    private Integer cinemaId;

    public Room(Integer id, Integer numOfSeats, Integer cinemaId) {
        super(id);
        this.numOfSeats = numOfSeats;
        this.cinemaId = cinemaId;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }
}
