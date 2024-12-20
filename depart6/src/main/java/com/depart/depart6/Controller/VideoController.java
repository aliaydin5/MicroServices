package com.depart.depart6.Controller;

import com.depart.depart6.Dto.VideoDto;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Entity.Video;
import com.depart.depart6.Repository.UserRepo;
import com.depart.depart6.Repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoRepo videoRepo;

    @Autowired
    UserRepo userRepo;








    private static final String VIDEO_DIR = "C:\\Users\\202002009007\\IdeaProjects\\MicroServicesNew\\videos\\";


    @PostMapping("/downloads/{userId}")
    public ResponseEntity<String> uploadVideo(@RequestBody MultipartFile file, @RequestParam("title") String title,@PathVariable long userId) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
           User user= userRepo.findById(userId);

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();


            Path path = Paths.get(VIDEO_DIR + fileName);


            Files.createDirectories(path.getParent());

            Files.write(path, file.getBytes());


            //String videoUrl = "http://your-server.com/videos/" + fileName;
            String videoUrl= String.valueOf(path);

           /* User user=userRepo.findByEmail(email);
            Set<Video> videos=user.getVideoSet();
            Video video1=new Video();
            video1.setUrl(videoUrl);
            video1.setTitle(title);
            videos.add(video1);
            user.setVideoSet(videos);*/



            Video video = new Video();
            video.setTitle(title);
            video.setVideo_url(videoUrl);
            video.setUser(user);
            videoRepo.save(video);

            return ResponseEntity.ok("Video uploaded successfully: " + videoUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload video");
        }
    }
    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> downloadVideosForUser(@PathVariable("userId") long userId) {
        List<Video> videoList = videoRepo.findAll().stream()
                .filter(video -> video.getUser().getId() == userId)
                .collect(Collectors.toList());

        if (!videoList.isEmpty()) {
            List<VideoDto> videoDtoList = new ArrayList<>();

            for (Video video : videoList) {
                VideoDto videoDto = new VideoDto();
                videoDto.setVideoUrl(video.getVideo_url());           // Set video URL
                videoDto.setName(video.getUser().getName());      // Set user's name from User table
                videoDto.setImage(video.getUser().getImage());  // Set profile image from User table
                videoDtoList.add(videoDto);
            }
            // VideoDto'ya mapleme işlemi
            /*List<VideoDto> videoDtoList = videoList.stream()
                    .map(video -> new VideoDto(
                            video.getVideo_url(),               // video URL
                            video.getUser().getName(),           // Kullanıcının adı (User tablosundan)
                            video.getVideo_url()         // Kullanıcının profil resmi (User tablosundan)
                    ))
                    .collect(Collectors.toList());*/

            return new ResponseEntity<>(videoDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No videos found for userId: " + userId, HttpStatus.NOT_FOUND);
        }

        /*List<Video> videoList = videoRepo.findAll().stream()
                .filter(video -> video.getUser().getId() == userId)
                .collect(Collectors.toList());

        if (!videoList.isEmpty()) {

            List<String> videoUrls = videoList.stream()
                    .map(Video::getVideo_url)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(videoUrls, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("No videos found for userId: " + userId, HttpStatus.NOT_FOUND);
        }*/
    }

    @PostMapping("/downloads/userPhoto/{userId}")
    public ResponseEntity<String> saveUserPhoto(@RequestBody MultipartFile file,@PathVariable long userId) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            User user= userRepo.findById(userId);

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();


            Path path = Paths.get(VIDEO_DIR + fileName);


            Files.createDirectories(path.getParent());

            Files.write(path, file.getBytes());


            //String videoUrl = "http://your-server.com/videos/" + fileName;
            String videoUrl= String.valueOf(path);
            user.setImage(videoUrl);
            userRepo.save(user);

           /* User user=userRepo.findByEmail(email);
            Set<Video> videos=user.getVideoSet();
            Video video1=new Video();
            video1.setUrl(videoUrl);
            video1.setTitle(title);
            videos.add(video1);
            user.setVideoSet(videos);*/



           /* Video video = new Video();
            video.setTitle(title);
            video.setVideo_url(videoUrl);
            video.setUser(user);
            videoRepo.save(video);*/

            return ResponseEntity.ok("Video uploaded successfully: " + videoUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload video");
        }
    }






}
