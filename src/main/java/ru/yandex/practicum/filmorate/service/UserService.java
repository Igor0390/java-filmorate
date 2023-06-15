package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public User getUserById(long id) {
        return userStorage.getUserById(id);
    }

    public User addFriend(long userId, long friendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);
        user.addFriend(friendId);
        friend.addFriend(userId);
        log.info("Пользователь " + user.getName() + " добавлен в список друзей " + friend.getName());
        return user;
    }

    public User deleteFriend(long userId, long friendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);
        user.deleteFriend(friendId);
        friend.deleteFriend(userId);
        log.info("Пользователь " + user.getName() + " удален из списка друзей " + friend.getName());
        return user;
    }

    public List<User> getFriends(long userId) {
        User user = userStorage.getUserById(userId);
        List<User> friendsList = new ArrayList<>();
        for (Long id : user.getFriends()) {
            friendsList.add(userStorage.getUserById(id));
        }
        log.info("Список друзей пользователя " + user.getName());
        return friendsList;
    }

    public List<User> corporateFriends(long userId, long friendId) {
        User user = userStorage.getUserById(userId);
        User friend = userStorage.getUserById(friendId);
        List<User> mutualFriends = new ArrayList<>();
        for (Long id : user.getFriends()) {
            if (friend.getFriends().contains(id)) {
                User mutualFriend = userStorage.getUserById((id));
                mutualFriends.add(mutualFriend);
            }
        }
        log.info("Список общих друзей пользователей");
        return mutualFriends;
    }
}
