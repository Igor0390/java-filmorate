package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class Genre {
    private Long id;
    @Size(max = 30)
    private String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(Long id) {
        this.id = id;
        this.name = "";
    }

    public Genre() {
        this.name = "";
    }
}
