package com.depart.depart6.Service;

import com.depart.depart6.Dto.CommentDto;
import com.depart.depart6.Entity.Comment;
import com.depart.depart6.Entity.Story;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Repository.CommentRepository;
import com.depart.depart6.Repository.StoryRepo;
import com.depart.depart6.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final StoryRepo storyRepo;
    private final UserRepo userRepository;
    @Autowired
    ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, StoryRepo storyRepo, UserRepo userRepository) {
        this.commentRepository = commentRepository;
        this.storyRepo=storyRepo;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long userId, Long storyId, String comment) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        Story story = storyRepo.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Gönderi bulunamadı"));

        Comment comment1 = new Comment();
        comment1.setUser(user);
        comment1.setStory(story);
        comment1.setComment(comment);
        comment1.setTime(LocalDateTime.now().toString());

        return commentRepository.save(comment1);
    }

    public List<CommentDto> getCommentsForPost(Long postId) {
       List<Comment> commentList= commentRepository.findByStoryId(postId);
        modelMapper.typeMap(Comment.class, CommentDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getImage(), CommentDto::setImage);
            mapper.map(src -> postId, CommentDto::setStoryId); // postId'yi storyId'ye setler
        });


        // Map the list of comments to a list of CommentDto and return
        return commentList.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());

    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Yorum bulunamadı"));
        comment.setIsDeleted(true);
        commentRepository.save(comment);
    }
}
