package com.depart.depart6.Dto;

import java.io.Serializable;

public class RabbitMessage implements Serializable {

    private int Id;

    private String name;

    public RabbitMessage(int id, String name) {
        Id = id;
        this.name = name;
    }

    public RabbitMessage() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
