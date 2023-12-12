package com.example.activityProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


public class ActivityDto {
    private long id;
    private String activityName;
    private LocalDate expireDate;
    private LocalDate beginDate;

    public ActivityDto(long id, String activityName, LocalDate expireDate, LocalDate beginDate) {
        this.id = id;
        this.activityName = activityName;
        this.expireDate = expireDate;
        this.beginDate = beginDate;
    }

    public ActivityDto() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
