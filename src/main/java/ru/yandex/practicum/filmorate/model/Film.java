package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Data
public class Film {
    @NonNull
    @NotNull
    private long id;
    @NonNull
    @NotBlank
    private String name;
    @NonNull
    @NotBlank
    private String description;
    @NonNull
    @NotNull
    private LocalDate releaseDate;
    @NonNull
    @NotNull
    @Positive
    private int duration;
    @NonNull
    @NotNull
    private Mpa mpa;

    private Set<Long> likes = new HashSet<>();

    private int likesCounter;

    public Film() {
    }


    public void addLike(Long id) {
        likes.add(id);
        likesCounter++;
    }

    public void deleteLike(Long id) {
        likes.remove(id);
        likesCounter--;
    }
}
