package com.depart.depart6.Controller;

import com.depart.depart6.Service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Beğeni ekle veya kaldır
    @PostMapping
    public String toggleLike(@RequestParam Long userId, @RequestParam Long storyId) {
        return likeService.toggleLike(userId, storyId);
    }

    // Gönderinin beğeni sayısını al
    @GetMapping("/{storyId}/count")
    public int getLikeCount(@PathVariable Long storyId) {
        return likeService.getLikeCount(storyId);
    }
}
