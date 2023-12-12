package com.example.activityProject.Repository;

import com.example.activityProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findById(long id);
    List<User> findAllById(long id);
    List<User> findByStatus(boolean status);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByUsernameOrEmail(String username,String email);
}
