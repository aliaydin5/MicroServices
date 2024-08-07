package com.depart.depart6.Repository;

import com.depart.depart6.Entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
