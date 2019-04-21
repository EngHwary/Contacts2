package com.example.nh.contacts;

public class Contact  {
    String name;
    String number;
    Integer id;

    public Contact(String name, String number, int id) {
        this.name = name;
        this.number = number;
        this.id =id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Integer getId() {
        return id;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;



    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
