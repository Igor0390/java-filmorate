package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Data
public class User {
    @NotNull
    private long id;
    @NonNull
    @Email
    @NotBlank
    private String email;
    @NonNull
    @NotBlank
    private String login;

    private String name;
    @NonNull
    @Past
    @NotNull
    private LocalDate birthday;

    public User() {
    }

    private Set<Long> friends = new HashSet<>();

    public void addFriend(Long id) {
        friends.add(id);
    }

    public void deleteFriend(Long id) {
        friends.remove(id);
    }

}
