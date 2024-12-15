package com.depart.depart6.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    private String name;

    private String surName;

    private String email;

    private String image;







    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Video> videoSet=new HashSet<>();


    @OneToMany(mappedBy = "user1",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Photo> photoSet=new HashSet<>();


    @OneToMany(mappedBy = "userMessage",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Message> messageSet=new ArrayList<>();



    @OneToMany(mappedBy = "userStory",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Story> storySet=new ArrayList<>();


}
