package com.example.demo.resource;

import com.example.demo.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

public class OrderResource extends BaseResource{
    private Integer id;
    private Integer cinema_id;
    private Integer film_id;
    private Integer room_id;
    private Integer quantity;
    private double discount;
    private String payment_method;
    private Integer client_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FilmResource film;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClientResource client;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RoomResource room;

    public OrderResource(){}

    public OrderResource(Order orderEntity){
        this.id = orderEntity.getId();
        this.cinema_id = orderEntity.getCinemaId();
        this.film_id = orderEntity.getFilmId();
        this.room_id = orderEntity.getRoomId();
        this.quantity = orderEntity.getQuantity();
        this.discount = orderEntity.getDiscount();
        this.payment_method = orderEntity.getPaymentMethod();
        this.client_id = orderEntity.getClientId();
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(Integer cinema_id) {
        this.cinema_id = cinema_id;
    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public FilmResource getFilm() {
        return film;
    }

    public void setFilm(FilmResource film) {
        this.film = film;
    }

    public ClientResource getClient() {
        return client;
    }

    public void setClient(ClientResource client) {
        this.client = client;
    }

    public RoomResource getRoom() {
        return room;
    }

    public void setRoom(RoomResource room) {
        this.room = room;
    }

    public Order toEntity(){
        return new Order(
                this.id,
                this.cinema_id,
                this.film_id,
                this.room_id,
                this.quantity,
                this.discount,
                this.payment_method,
                this.client_id
        );
    }
}
