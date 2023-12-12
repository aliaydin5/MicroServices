package com.example.activityProject.Repository;

import com.example.activityProject.Entity.Activity;
import com.example.activityProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    @Query(value = "SELECT * FROM activity U WHERE U.status=?1",nativeQuery = true)
    Activity findById(long id);
    List<Activity> findAllById(long id);


}
