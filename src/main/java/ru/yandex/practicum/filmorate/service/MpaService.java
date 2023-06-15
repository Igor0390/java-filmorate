package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.storage.db.MpaStorage;

import java.util.List;

@Slf4j
@Service
public class MpaService {
    private final MpaStorage storage;

    @Autowired
    public MpaService(MpaStorage storage) {
        this.storage = storage;
    }

    public Mpa getById(int id) {
        if (id < 0) {
            throw new NotFoundException("Неверно передан ID MPA.");
        }
        log.info("Получен рейтинг MPA с идентификатором " + id + ".");
        return storage.getById(id);
    }

    public List<Mpa> getAll() {
        log.info("Получен список всех рейтингов MPA.");
        return storage.getAll();
    }
}
