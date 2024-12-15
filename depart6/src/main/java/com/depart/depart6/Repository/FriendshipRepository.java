package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Friendship;
import com.depart.depart6.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    // Belirli bir kullanıcı ve arkadaş arasındaki ilişkiyi bul
    Optional<Friendship> findByUserAndFriend(User user, User friend);

    // Kullanıcı ve arkadaş arasında mevcut bir ilişki varsa kontrol et
    boolean existsByUserAndFriend(User user, User friend);
}
