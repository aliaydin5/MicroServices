package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartRepository extends JpaRepository<Depart,Long> {





}
