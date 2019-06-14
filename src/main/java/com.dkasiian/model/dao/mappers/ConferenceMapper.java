package com.dkasiian.model.dao.mappers;

import com.dkasiian.model.dto.ConferenceDto;
import com.dkasiian.model.entities.Conference;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConferenceMapper implements Mapper<Conference> {

    @Override
    public Conference mapToObject(ResultSet resultSet, String language) throws SQLException {
        return new Conference.Builder()
                .setId(resultSet.getLong("conference_id"))
                .setName(resultSet.getString("conference_name_" + language))
                .setLocation(resultSet.getString("conference_location_" + language))
                .setDateTime(resultSet.getTimestamp("conference_datetime").toLocalDateTime())
                .build();
    }

    public ConferenceDto mapToDtoObject(ResultSet resultSet) throws SQLException {
        return new ConferenceDto.Builder()
                .setId(resultSet.getLong("conference_id"))
                .setNameEn(resultSet.getString("conference_name_en_US"))
                .setNameUa(resultSet.getString("conference_name_uk_UA"))
                .setLocationEn(resultSet.getString("conference_location_en_US"))
                .setLocationUa(resultSet.getString("conference_location_uk_UA"))
                .setDateTime(resultSet.getTimestamp("conference_datetime").toLocalDateTime())
                .build();
    }

}


