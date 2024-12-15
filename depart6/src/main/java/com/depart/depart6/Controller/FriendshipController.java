package com.depart.depart6.Controller;

import com.depart.depart6.Entity.Friendship;
import com.depart.depart6.Service.FriendshipService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friendship")
public class FriendshipController {
    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @PostMapping("/send")
    public void sendFriendRequest(@RequestParam Long senderId, @RequestParam Long receiverId) {
        friendshipService.sendFriendRequest(senderId, receiverId);
    }

    @PostMapping("/accept")
    public void acceptFriendRequest(@RequestParam Long friendshipId) {
        friendshipService.acceptFriendRequest(friendshipId);
    }

    @PostMapping("/decline")
    public void declineFriendRequest(@RequestParam Long friendshipId) {
        friendshipService.declineFriendRequest(friendshipId);
    }

    @PostMapping("/block")
    public void blockUser(@RequestParam Long senderId, @RequestParam Long receiverId) {
        friendshipService.blockUser(senderId, receiverId);
    }

    @GetMapping("/status")
    public Friendship.FriendshipStatus checkStatus(@RequestParam Long userId, @RequestParam Long friendId) {
        return friendshipService.checkFriendshipStatus(userId, friendId);
    }
}
