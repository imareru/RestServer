package com.example.demo.resource;

import com.example.demo.entity.Room;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RoomResource extends BaseResource{
    private Integer id;
    private Integer numOfSeats;
    private Integer cinemaId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CinemaResource cinema;

    public RoomResource(){}

    public RoomResource(Room roomEntity){
        this.id = roomEntity.getId();
        this.numOfSeats = roomEntity.getNumOfSeats();
        this.cinemaId = roomEntity.getCinemaId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public CinemaResource getCinema() {
        return cinema;
    }

    public void setCinema(CinemaResource cinema) {
        this.cinema = cinema;
    }

    public Room toEntity(){
        return new Room(
                this.id,
                this.numOfSeats,
                this.cinemaId
        );
    }
}
