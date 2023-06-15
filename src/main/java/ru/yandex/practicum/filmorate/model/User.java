package ru.yandex.practicum.filmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<Long> friends = new HashSet<>();

    public User() {

    }


    public void addFriend(Long id) {
        friends.add(id);
    }

    public void deleteFriend(Long id) {
        friends.remove(id);
    }

    public List<Long> getFriends() {
        return new ArrayList<>(friends);
    }

    public boolean containsFriend(Long id) {
        return friends.contains(id);
    }

}
