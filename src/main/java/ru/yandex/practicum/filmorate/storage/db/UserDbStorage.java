package ru.yandex.practicum.filmorate.storage.db;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Primary
@Component
public class UserDbStorage implements UserStorage {
    private final JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User addUser(User user) {
        String sql = "INSERT INTO USERS (EMAIL, LOGIN, NAME, BIRTHDAY) values(?,?,?,?)";
        GeneratedKeyHolder keyHolder;
        keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"USER_ID"});
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getName());
            stmt.setDate(4, Date.valueOf(user.getBirthday()));
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public User updateUser(User user) {
        String sql =
                "UPDATE USERS SET LOGIN = ?, EMAIL = ?, NAME = ?, BIRTHDAY = ?" +
                        "WHERE USER_ID = ?";
        jdbcTemplate.update(sql, user.getLogin(), user.getEmail(), user.getName(), user.getBirthday(), user.getId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USERS ORDER BY USER_ID";
        return jdbcTemplate.query(sql, this::mapToUser);
    }

    @Override
    public User getUserById(long userId) {
        String sql = "SELECT * FROM USERS WHERE USER_ID = ?";
        List<User> result = jdbcTemplate.query(sql, this::mapToUser, userId);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public boolean containsFriendship(Long filterId1, Long filterId2, Boolean filterConfirmed) {
        String sql = "SELECT * FROM FRIENDSHIP WHERE USER_ID1 = ? AND USER_ID2 = ? AND  CONFIRMED = ?";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, filterId1, filterId2, filterConfirmed);
        return rows.next();
    }

    @Override
    public void updateFriendship(Long id1, Long id2, boolean confirmed, Long filterId1, Long filterId2) {
        String sql =
                "UPDATE FRIENDSHIP SET USER_ID1 = ?, USER_ID2 = ?, CONFIRMED = ? " +
                        "WHERE USER_ID1 = ? AND USER_ID2 = ?";
        jdbcTemplate.update(sql, id1, id2, confirmed, filterId1, filterId2);
    }

    @Override
    public void insertFriendship(Long id, Long friendId) {
        String sql = "INSERT INTO FRIENDSHIP (USER_ID1, USER_ID2, CONFIRMED) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, id, friendId, false);
    }

    private User mapToUser(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("USER_ID"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setLogin(resultSet.getString("LOGIN"));
        user.setName(resultSet.getString("NAME"));
        user.setBirthday(resultSet.getDate("BIRTHDAY").toLocalDate());
        return user;
    }
}
