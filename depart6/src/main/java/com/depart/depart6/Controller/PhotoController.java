package com.depart.depart6.Controller;

import com.depart.depart6.Dto.VideoDto;
import com.depart.depart6.Entity.Photo;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Entity.Video;
import com.depart.depart6.Repository.PhotoRepo;
import com.depart.depart6.Repository.UserRepo;
import com.depart.depart6.Repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {

    @Autowired
    PhotoRepo photoRepo;

    @Autowired
    UserRepo userRepo;



    private static final String VIDEO_DIR = "C:\\Users\\202002009007\\IdeaProjects\\MicroServicesNew\\videos\\";


    @PostMapping("/downloads/{userId}")
    public ResponseEntity<String> uploadVideo(@RequestBody MultipartFile file, @RequestParam("title") String title, @PathVariable long userId) {

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


            Photo photo=new Photo();
            photo.setUser1(user);
            photo.setVideo_url(videoUrl);
            photo.setTitle(title);
            photoRepo.save(photo);

            return ResponseEntity.ok("Video uploaded successfully: " + videoUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload video");
        }
    }
    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> downloadVideosForUser(@PathVariable("userId") long userId) {
        List<Photo> photoList = photoRepo.findAll().stream()
                .filter(photo -> photo.getUser1().getId() == userId)
                .collect(Collectors.toList());

        if (!photoList.isEmpty()) {
            // VideoDto'ya mapleme işlemi
            /*List<VideoDto> videoDtoList = videoList.stream()
                    .map(video -> new VideoDto(
                            video.getVideo_url(),               // video URL
                            video.getUser().getName(),           // Kullanıcının adı (User tablosundan)
                            video.getUser().getImage()           // Kullanıcının profil resmi (User tablosundan)
                    ))
                    .collect(Collectors.toList());*/

            return new ResponseEntity<>(photoList, HttpStatus.OK);
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



}
