package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo,Long> {
}
