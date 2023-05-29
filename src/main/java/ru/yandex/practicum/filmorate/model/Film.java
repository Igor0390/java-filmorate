package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class Film {
    @NotNull
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate releaseDate;
    @NotNull
    @Positive
    private int duration;
    @Positive
    private Set<Integer> likes = new HashSet<>();
    @Positive
    private int likesCounter;

    public void addLike(Integer id) {
        likes.add(id);
        likesCounter++;
    }

    public void deleteLike(Integer id) {
        likes.remove(id);
        likesCounter--;
    }
}
