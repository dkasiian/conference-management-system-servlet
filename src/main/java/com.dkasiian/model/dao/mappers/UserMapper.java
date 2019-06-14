package com.dkasiian.model.dao.mappers;

import com.dkasiian.model.entities.Role;
import com.dkasiian.model.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User mapToObject(ResultSet resultSet, String language) throws SQLException {
        return new User.Builder()
                .setId(resultSet.getLong("user_id"))
                .setLogin(resultSet.getString("user_login"))
                .setName(resultSet.getString("user_name_" + language))
                .setSurname(resultSet.getString("user_surname_" + language))
                .setPassword(resultSet.getString("user_password"))
                .setEmail(resultSet.getString("user_email"))
                .setRole(Role.valueOf(resultSet.getString("user_role")))
                .build();
    }
}
