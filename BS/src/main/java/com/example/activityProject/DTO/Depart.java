package com.example.activityProject.DTO;

public class Depart {
    private long id;
    private  String departName;

    public Depart(long id, String departName) {
        this.id = id;
        this.departName = departName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
