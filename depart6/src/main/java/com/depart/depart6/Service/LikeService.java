package com.depart.depart6.Service;

import com.depart.depart6.Entity.Like;
import com.depart.depart6.Entity.Story;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Repository.LikeRepository;
import com.depart.depart6.Repository.StoryRepo;
import com.depart.depart6.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final StoryRepo storyRepo;
    private final UserRepo userRepository;

    public LikeService(LikeRepository likeRepository, StoryRepo storyRepo, UserRepo userRepository) {
        this.likeRepository = likeRepository;
        this.storyRepo=storyRepo;
        this.userRepository = userRepository;
    }

    // Beğeni ekle veya kaldır
    public String toggleLike(Long userId, Long storyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Story story = storyRepo.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Gönderi bulunamadı"));

        Optional<Like> existingLike = likeRepository.findByUserIdAndPostId(userId, storyId);

        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
            return "Beğeni kaldırıldı.";
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setStory(story);
            likeRepository.save(like);
            return "Gönderi beğenildi.";
        }
    }

    // Gönderinin toplam beğeni sayısını getir
    public int getLikeCount(Long storyId) {
        return likeRepository.countByStoryId(storyId);
    }
}
