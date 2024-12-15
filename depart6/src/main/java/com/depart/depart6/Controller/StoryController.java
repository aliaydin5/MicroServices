package com.depart.depart6.Controller;

import com.depart.depart6.Dto.StoryDto;
import com.depart.depart6.Dto.VideoDto;
import com.depart.depart6.Entity.Story;
import com.depart.depart6.Entity.Video;
import com.depart.depart6.Service.StoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/story")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    // Kullanıcının konumuna göre yakınlık sırasına göre storyleri listeleyen endpoint
    @GetMapping("/nearby")
    public List<StoryDto> getNearbyStories(@RequestParam double latitude, @RequestParam double longitude) {
        return storyService.getStoriesSortedByProximity(latitude, longitude);
    }

    @PostMapping("/savestory/{id}")
    public ResponseEntity<?>  saveStory(@RequestBody StoryDto storyDto,@PathVariable long id) {
        return  new ResponseEntity<>(storyService.saveStory(storyDto,id),HttpStatus.OK);

    }





}
