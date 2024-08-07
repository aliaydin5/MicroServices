package com.depart.depart6.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private String url;


    private String title;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id",referencedColumnName = "id")
    private User user;

}
