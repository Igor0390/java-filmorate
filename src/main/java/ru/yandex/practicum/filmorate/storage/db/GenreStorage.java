package ru.yandex.practicum.filmorate.storage.db;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;


import java.util.List;
import java.util.Set;

public interface GenreStorage {
    Set<Genre> getGenresByFilm(Film film);
    Genre getById(int id);

    List<Genre> getAll();

    List<Genre> getGenresByFilmId(int filmId);

    void filmGenreUpdate(Integer filmId, List<Genre> genreList);

    void deleteGenresByFilmId(int filmId);
}
