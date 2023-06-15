package ru.yandex.practicum.filmorate.storage.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    User addUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    User getUserById(long userId);

    boolean containsFriendship(Long filterId1, Long filterId2, Boolean filterConfirmed);

    void updateFriendship(Long id1, Long id2, boolean confirmed, Long filterId1, Long filterId2);

    void insertFriendship(Long id, Long friendId);
}
