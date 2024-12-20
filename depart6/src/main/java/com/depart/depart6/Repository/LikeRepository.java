package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndStoryId(Long userId, Long storyId); // Kullanıcı bir gönderiyi daha önce beğenmiş mi kontrol etmek için
    int countByStoryId(Long storyId); // Gönderideki toplam beğeni sayısını öğrenmek için
}
