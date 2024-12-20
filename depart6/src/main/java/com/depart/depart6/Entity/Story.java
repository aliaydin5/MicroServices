package com.depart.depart6.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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



    @Column(length = 500)
    private String caption; // Gönderi açıklaması

    @ElementCollection
    @CollectionTable(name = "story_images", joinColumns = @JoinColumn(name = "story_id"))
    @Column(name = "image_path")
    private List<String> images;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id",referencedColumnName = "id")
    @JsonBackReference
    private User userStory;


    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments; // Gönderiye ait yorumlar



    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes; // Gönderiye ait beğeniler



    public int getLikeCount() {
        return likes != null ? likes.size() : 0; // Beğeni sayısı
    }



    private double latitude;
    private double longitude;
}
