package com.depart.depart6.Dto;


public class VideoDto {

    private String videoUrl;
    private String name;
    private String image;


    public VideoDto() {
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
