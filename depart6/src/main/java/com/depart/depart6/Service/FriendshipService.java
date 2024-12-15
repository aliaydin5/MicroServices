package com.depart.depart6.Service;

import com.depart.depart6.Entity.Friendship;
import com.depart.depart6.Entity.User;
import com.depart.depart6.Repository.FriendshipRepository;
import com.depart.depart6.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final UserRepo userRepository;

    public FriendshipService(FriendshipRepository friendshipRepository, UserRepo userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public void sendFriendRequest(Long senderId, Long receiverId) {
        // Kullanıcıları kontrol et
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Gönderen kullanıcı bulunamadı"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Alıcı kullanıcı bulunamadı"));

        // Zaten mevcut bir ilişki varsa hata fırlat
        if (friendshipRepository.existsByUserAndFriend(sender, receiver)) {
            throw new RuntimeException("Zaten arkadaşlık isteği gönderildi.");
        }

        // Yeni arkadaşlık isteği oluştur
        Friendship friendship = new Friendship();
        friendship.setUser(sender);
        friendship.setFriend(receiver);
        friendship.setStatus(Friendship.FriendshipStatus.PENDING);

        // Veritabanına kaydet
        friendshipRepository.save(friendship);
    }


    @Transactional
    public void acceptFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Arkadaşlık isteği bulunamadı"));

        // İlişki durumunu ACCEPTED yap
        friendship.setStatus(Friendship.FriendshipStatus.ACCEPTED);
        friendshipRepository.save(friendship);
    }

    /**
     * Arkadaşlık isteğini reddet
     */
    @Transactional
    public void declineFriendRequest(Long friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Arkadaşlık isteği bulunamadı"));

        // İlişki durumunu DECLINED yap
        friendship.setStatus(Friendship.FriendshipStatus.DECLINED);
        friendshipRepository.save(friendship);
    }

    /**
     * Kullanıcıyı engelle
     */
    @Transactional
    public void blockUser(Long senderId, Long receiverId) {
        // Gönderen ve alıcı kullanıcıyı kontrol et
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Gönderen kullanıcı bulunamadı"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Alıcı kullanıcı bulunamadı"));

        // Yeni bir ilişki oluştur veya mevcut olanı al
        Friendship friendship = friendshipRepository
                .findByUserAndFriend(sender, receiver)
                .orElse(new Friendship());

        friendship.setUser(sender);
        friendship.setFriend(receiver);
        friendship.setStatus(Friendship.FriendshipStatus.BLOCKED);

        // Veritabanına kaydet
        friendshipRepository.save(friendship);
    }

    public Friendship.FriendshipStatus checkFriendshipStatus(Long userId, Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Arkadaş kullanıcı bulunamadı"));

        return friendshipRepository.findByUserAndFriend(user, friend)
                .map(Friendship::getStatus)
                .orElse(null);
    }
}
