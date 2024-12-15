package com.depart.depart6.Controller;

import com.depart.depart6.Entity.User;
import com.depart.depart6.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/getAllImages/{id}")
    public ResponseEntity<List<String>> getAllImages(@PathVariable long id){
        List<String> userImages = userRepo.findAll().stream()
                .filter(user -> user.getId() == id) // Kullanıcı ID'sine göre filtrele
                .flatMap(user -> user.getStorySet().stream()) // Kullanıcının story set'ini düzleştir
                .flatMap(story -> story.getImages().stream()) // Story'nin içindeki images listesini düzleştir
                .collect(Collectors.toList());

        return new ResponseEntity<>(userImages, HttpStatus.OK);
    }



    // Kullanıcı adında LIKE sorgusuyla filtre yapıp sonuç dönen endpoint
    @GetMapping("/searchUsersByUsername/{username}")
    public ResponseEntity<List<User>> searchUsersByUsername(@PathVariable String username) {
        // LIKE sorgusuyla kullanıcıları alıyoruz
        List<User> users = userRepo.findByUsernameLike(username);

        // Kullanıcı listesi boşsa 404 döner
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Eşleşen kullanıcı bulunamadı");
        }

        // Kullanıcılar bulunursa döndür
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
