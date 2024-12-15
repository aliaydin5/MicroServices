package com.depart.depart6.Entity;

import com.depart.depart6.Enum.FriendshipStatus;
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
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // İsteği gönderen kullanıcı

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User friend; // İsteği alan kullanıcı
    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;


    // Enum tanımı burada
    public enum FriendshipStatus {
        PENDING,    // İstek gönderildi, cevap bekleniyor
        ACCEPTED,   // İstek kabul edildi
        DECLINED,   // İstek reddedildi
        BLOCKED     // Kullanıcı engellendi
    }


    // Getters and Setters...


}
