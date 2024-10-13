package com.depart.depart6.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String video_url;


    private String title;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id",referencedColumnName = "id")
    private User user1;



}
