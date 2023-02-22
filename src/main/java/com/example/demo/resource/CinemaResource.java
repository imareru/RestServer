package com.example.demo.resource;

import com.example.demo.entity.Cinema;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CinemaResource extends BaseResource {
    private Integer id;
    private String name;
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RoomResource[] room;

    public CinemaResource(){}

    public CinemaResource(Cinema cinemaEntity){
        this.id = cinemaEntity.getId();
        this.name = cinemaEntity.getName();
        this.address = cinemaEntity.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RoomResource[] getRoom() {
        return room;
    }

    public void setRoom(RoomResource[] room) {
        this.room = room;
    }

    public Cinema toEntity(){
        return new Cinema(
                this.id,
                this.name,
                this.address
        );
    }
}
