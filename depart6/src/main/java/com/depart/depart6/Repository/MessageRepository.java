package com.depart.depart6.Repository;

import com.depart.depart6.Dto.MessageDto;
import com.depart.depart6.Entity.Message;
import com.depart.depart6.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByTimeAsc(
            String senderId, String receiverId, String receiverId2, String senderId2);


    // İki kullanıcı arasındaki son mesajları getirir
   /* @Query("SELECT m FROM Message m WHERE (m.senderId = :userId OR m.receiverId = :userId) " +
            "AND m.time = (SELECT MAX(m2.time) FROM Message m2 WHERE (m2.senderId = m.senderId AND m2.receiverId = m.receiverId) OR (m2.senderId = m.receiverId AND m2.receiverId = m.senderId))")
    List<Message> findLastMessagesForUser(@Param("userId") String userId);*/


    List<Message> findBySenderIdOrderByTimeAsc(User user);

    //List<Message> findBySender()



}
