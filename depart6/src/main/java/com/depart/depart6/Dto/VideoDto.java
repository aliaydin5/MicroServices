package com.depart.depart6.Dto;

import com.depart.depart6.Entity.User;
import com.depart.depart6.Entity.Video;
import lombok.Data;

import java.util.List;


public class VideoDto {

    private String videoUrl;
    private String name;
    private String image;


    public VideoDto(String videoUrl, String name, String image) {
        this.videoUrl = videoUrl;
        this.name = name;
        this.image = image;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}