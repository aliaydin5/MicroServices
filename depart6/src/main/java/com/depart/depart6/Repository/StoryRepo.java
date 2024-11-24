package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepo extends JpaRepository<Story,Long> {


}
