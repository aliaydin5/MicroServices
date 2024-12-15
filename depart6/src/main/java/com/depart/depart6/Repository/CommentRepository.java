package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId); // Belirli bir gönderiye ait yorumları al

}
