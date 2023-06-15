package ru.yandex.practicum.filmorate.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Mpa;

import javax.validation.ValidationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FilmControllerTest {
    @Autowired
    private FilmController filmController;
    private Film film;
    private Film film2;

    @BeforeEach
    public void beforeEach() {
        film = new Film();
        film.setId(1);
        film.setName("1111");
        film.setDescription("dddddd");
        film.setReleaseDate(LocalDate.of(2010, 9, 03));
        film.setDuration(190);
        film.setMpa(new Mpa(0L, "Null"));

        film2 = new Film();
        film2.setId(2);
        film2.setName("2");
        film2.setDescription("dddddd");
        film2.setReleaseDate(LocalDate.of(2010, 9, 03));
        film2.setDuration(190);
        film2.setMpa(new Mpa(0L, "Null"));

    }

    @Test
    public void addFilm() {
        filmController.addFilm(film);
        assertEquals(filmController.getAllFilms().get(0).getName(), film.getName());
        assertEquals(filmController.getAllFilms().size(), 1);
    }
}