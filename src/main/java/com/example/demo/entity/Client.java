package com.example.demo.entity;

public class Client extends BaseEntity{
    private String name;
    private String phone;
    private String mail;

    public Client(Integer id, String name, String phone, String mail) {
        super(id);
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
