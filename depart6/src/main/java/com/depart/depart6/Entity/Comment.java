package com.depart.depart6.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Yorumu yazan kullanıcı

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story; // Yoruma ait olan gönderi (örneğin, resim)

    @Column(nullable = false, length = 1000)
    private String comment; // Yorum içeriği

    @Column(nullable = false)
    private LocalDateTime time; // Yorumun oluşturulma zamanı

    @Column(nullable = false)
    private Boolean isDeleted = false; // Silinmiş mi durumu
}
