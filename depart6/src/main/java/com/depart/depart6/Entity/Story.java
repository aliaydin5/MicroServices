package com.depart.depart6.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    @Column(name = "is_online")
    private boolean isOnline;

    private String time;

    @ElementCollection
    @CollectionTable(name = "story_images", joinColumns = @JoinColumn(name = "story_id"))
    @Column(name = "image_path")
    private List<String> images;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id",referencedColumnName = "id")
    private User userStory;



    private double latitude;
    private double longitude;
}
