package com.depart.depart6.Controller;

import com.depart.depart6.Entity.User;
import com.depart.depart6.Entity.Video;
import com.depart.depart6.Repository.UserRepo;
import com.depart.depart6.Repository.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoRepo videoRepo;

    @Autowired
    UserRepo userRepo;


    @Autowired





    private static final String VIDEO_DIR = "C:\\MicroServices\\depart6\\videos\\";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestBody MultipartFile file, @RequestParam("title") String title) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {

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
            video.setUrl(videoUrl);
            videoRepo.save(video);

            return ResponseEntity.ok("Video uploaded successfully: " + videoUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload video");
        }
    }
    @PostMapping("/downloads")
    public ResponseEntity<String> downloadsUser(@RequestBody MultipartFile multipartFile,@RequestParam Long id,@RequestBody String Latitude,@RequestBody String Longitude){

        return null;
    }




}
