package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Genre getById(Long id) {
        String sql = "SELECT * FROM GENRES WHERE GENRE_ID = ?";
        List<Genre> result = jdbcTemplate.query(sql, this::mapToGenre, id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    private Genre mapToGenre(ResultSet resultSet, int rowNum) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong("GENRE_ID"));
        genre.setName(resultSet.getString("NAME"));
        return genre;
    }


    public List<Genre> getAll() {
        String sql = "SELECT * FROM GENRES ORDER BY GENRE_ID";
        return jdbcTemplate.query(sql, this::mapToGenre);
    }


    public Genre create(Genre genre) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("GENRES")
                .usingGeneratedKeyColumns("GENRE_ID");

        Map<String, Object> values = new HashMap<>();
        values.put("NAME", genre.getName());

        genre.setId(simpleJdbcInsert.executeAndReturnKey(values).longValue());
        return genre;
    }


    public Genre update(Genre genre) {
        String sql = "UPDATE GENRES SET NAME = ? WHERE GENRE_ID = ?";
        jdbcTemplate.update(sql, genre.getName(), genre.getId());
        return genre;
    }


    public Set<Genre> getGenresByFilm(Film film) {
        String sql = "SELECT g.GENRE_ID, g.NAME FROM GENRES g NATURAL JOIN FILMS_GENRES fg WHERE fg.FILM_ID = ?";
        return new HashSet<>(jdbcTemplate.query(sql, this::mapToGenre, film.getId()));
    }
}
