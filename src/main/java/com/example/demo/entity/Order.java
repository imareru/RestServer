package com.example.demo.entity;

public class Order extends BaseEntity{
    private Integer cinemaId;
    private Integer filmId;
    private Integer roomId;
    private Integer quantity;
    private Double discount;
    private String paymentMethod;
    private Integer clientId;

    public Order(Integer id, Integer cinemaId, Integer filmId, Integer roomId, Integer quantity, Double discount, String paymentMethod, Integer clientId) {
        super(id);
        this.cinemaId = cinemaId;
        this.filmId = filmId;
        this.roomId = roomId;
        this.quantity = quantity;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
        this.clientId = clientId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
