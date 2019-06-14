package com.dkasiian.model.dao.mappers;

import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.entities.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportMapper implements Mapper<Report> {

    @Override
    public Report mapToObject(ResultSet resultSet, String language) throws SQLException {
        return new Report.Builder()
                .setId(resultSet.getLong("report_id"))
                .setTheme(resultSet.getString("report_theme_" + language))
                .setDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime())
                .setSpeakerId(resultSet.getLong("speaker_id"))
                .build();
    }

    public ReportDto mapToDtoObject(ResultSet resultSet) throws SQLException {
        return new ReportDto.Builder()
                .setId(resultSet.getLong("report_id"))
                .setThemeEn(resultSet.getString("report_theme_en_US"))
                .setThemeUa(resultSet.getString("report_theme_uk_UA"))
                .setDateTime(resultSet.getTimestamp("report_datetime").toLocalDateTime())
                .setSpeakerId(resultSet.getLong("speaker_id"))
                .build();
    }

}
