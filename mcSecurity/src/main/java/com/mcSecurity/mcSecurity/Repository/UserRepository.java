package com.mcSecurity.mcSecurity.Repository;

import com.mcSecurity.mcSecurity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findById(long id);
    List<User> findAllById(long id);
    List<User> findByStatus(boolean status);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByUsernameOrEmail(String username,String email);
}
