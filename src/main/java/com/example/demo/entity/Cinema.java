package com.example.demo.entity;

public class Cinema extends BaseEntity{
    private String name;
    private String address;

    public Cinema(Integer id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
