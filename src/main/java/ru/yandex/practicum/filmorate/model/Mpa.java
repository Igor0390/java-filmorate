package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
public class Mpa {
    private long id;
    @Size(max = 10)
    private String name;

    public Mpa(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Mpa(Long id) {
        this.id = id;
        this.name = "";
    }

    public Mpa() {
        this.name = "";
    }
}