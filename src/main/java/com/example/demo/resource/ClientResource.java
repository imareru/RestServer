package com.example.demo.resource;

import com.example.demo.entity.Client;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ClientResource extends BaseResource{
    private Integer id;
    private String name;
    private String phone;
    private String mail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderResource[] order;

    public ClientResource(){}

    public ClientResource(Client clientEntity){
        this.id = clientEntity.getId();
        this.name = clientEntity.getName();
        this.phone = clientEntity.getPhone();
        this.mail = clientEntity.getMail();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public OrderResource[] getOrder(){
        return order;
    }

    public void setOrder(OrderResource[] order) {
        this.order = order;
    }

    public Client toEntity(){
        return new Client(
                this.id,
                this.name,
                this.phone,
                this.mail
        );
    }
}
