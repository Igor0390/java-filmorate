package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Mpa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MpaStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Mpa findById(Long id) {
        String sql = "SELECT * FROM RATINGS WHERE RATING_ID = ?";
        List<Mpa> result = jdbcTemplate.query(sql, this::mapToRating, id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    private Mpa mapToRating(ResultSet resultSet, int rowNum) throws SQLException {
        Mpa mpa = new Mpa();
        mpa.setId(resultSet.getLong("RATING_ID"));
        mpa.setName(resultSet.getString("NAME"));
        return mpa;
    }


    public List<Mpa> getAll() {
        String sql = "SELECT * FROM RATINGS ORDER BY RATING_ID";
        return jdbcTemplate.query(sql, this::mapToRating);
    }


    public Mpa create(Mpa mpa) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("RATINGS")
                .usingGeneratedKeyColumns("RATING_ID");

        Map<String, Object> values = new HashMap<>();
        values.put("NAME", mpa.getName());

        mpa.setId(simpleJdbcInsert.executeAndReturnKey(values).longValue());
        return mpa;
    }


    public Mpa update(Mpa mpa) {
        String sql = "UPDATE RATINGS SET NAME = ? WHERE RATING_ID = ?";
        jdbcTemplate.update(sql, mpa.getName(), mpa.getId());
        return mpa;
    }

    public Mpa getById(int id) {
        String sql = "SELECT * FROM RATINGS WHERE RATING_ID = ?";
        List<Mpa> result = jdbcTemplate.query(sql, this::mapToRating, id);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
