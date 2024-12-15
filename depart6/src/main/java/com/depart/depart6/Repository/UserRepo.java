package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Message;
import com.depart.depart6.Entity.User;
import jakarta.persistence.Id;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User findById(long ıd);

    // Kullanıcı adında LIKE sorgusu yapar
    @Query("SELECT u FROM User u WHERE u.name LIKE %:username%")
    List<User> findByUsernameLike(@Param("username") String username);


}
