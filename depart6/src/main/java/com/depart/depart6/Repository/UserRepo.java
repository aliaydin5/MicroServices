package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Message;
import com.depart.depart6.Entity.User;
import jakarta.persistence.Id;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);

    User findById(long ıd);


}
