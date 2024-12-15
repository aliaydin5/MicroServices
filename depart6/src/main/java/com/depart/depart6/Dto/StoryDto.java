package com.depart.depart6.Dto;

import com.depart.depart6.Entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class StoryDto {
    private String name;


    private String image;

    private Long id;

    private String status;

    private boolean isOnline;

    private String time;


    private List<String> images;







    private double latitude;
    private double longitude;
}
