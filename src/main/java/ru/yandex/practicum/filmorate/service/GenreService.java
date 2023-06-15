package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.storage.db.GenreStorage;

import java.util.List;
@Slf4j
@Service
public class GenreService {
    private final GenreStorage storage;

    @Autowired
    public GenreService(GenreStorage storage) {
        this.storage = storage;
    }
    public Genre getById(int id) {
        if (id < 0) {
            throw new NotFoundException("Неверно передан ID Genre.");
        }
        log.info("Получен жанр с идентификатором " + id + ".");
        return storage.getById(id);
    }

    public List<Genre> getAll() {
        log.info("Получен список всех жанров.");
        return storage.getAll();
    }
}
