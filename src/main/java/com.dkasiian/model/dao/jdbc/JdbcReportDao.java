package com.dkasiian.model.dao.jdbc;

import com.dkasiian.model.ResourceName;
import com.dkasiian.model.dao.ReportDao;
import com.dkasiian.model.dao.mappers.Mapper;
import com.dkasiian.model.dao.mappers.ReportMapper;
import com.dkasiian.model.dto.ReportDto;
import com.dkasiian.model.entities.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class JdbcReportDao implements ReportDao {

    private static final Logger LOG = LogManager.getLogger(JdbcReportDao.class);
    private DataSource dataSource = ConnectionPool.getDataSource();
    private ResourceBundle sqlStatementsBundle = ResourceBundle.getBundle(ResourceName.SQL_STATEMENTS_BUNDLE);
    private Mapper<Report> reportMapper = new ReportMapper();

    @Override
    public boolean add(Report item) { throw new UnsupportedOperationException(); }

    @Override
    public void update(Report item) { throw new UnsupportedOperationException(); }

    @Override
    public boolean delete(long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.delete.by.id"))) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            LOG.error("JdbcReportDao :: delete :: " + e);
        }
        return false;
    }

    @Override
    public Report getById(long id, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.select.by.id"))) {
            Report report = null;
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                report = reportMapper.mapToObject(resultSet, language);
            return report;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getById :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Report> getAll(String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.select.all"))) {
            List<Report> reports = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                reports.add(reportMapper.mapToObject(resultSet, language));
            return reports;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getAll :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Report> getAllLinkedToConference(long conferenceId, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.select.all.linked.to.conference"))) {
            List<Report> reports = new ArrayList<>();
            preparedStatement.setLong(1, conferenceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                reports.add(reportMapper.mapToObject(resultSet, language));
            return reports;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getAllLinkedToConference :: " + exc);
            throw new RuntimeException();
        }
    }

    public ReportDto getReportDtoById(long reportId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.select.by.id"))) {
            ReportDto reportDto = null;
            preparedStatement.setLong(1, reportId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                reportDto = new ReportMapper().mapToDtoObject(resultSet);
            return reportDto;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getReportDtoById :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void add(long conferenceId, ReportDto report) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement reportStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.add"), Statement.RETURN_GENERATED_KEYS);
             PreparedStatement reportsConferencesStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.conferences.insert"))) {
            connection.setAutoCommit(false);

            reportStatement.setString(1, report.getThemeEn());
            reportStatement.setString(2, report.getThemeUa());
            reportStatement.setTimestamp(3, Timestamp.valueOf(report.getDateTime()));
            reportStatement.setLong(4, report.getSpeakerId());
            int affectedRows = reportStatement.executeUpdate();
            if (affectedRows == 0)
                throw new SQLException("Creating user failed, no rows affected.");

            try (ResultSet generatedKeys = reportStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reportsConferencesStatement.setLong(1, generatedKeys.getLong(1));
                    reportsConferencesStatement.setLong(2, conferenceId);
                    reportsConferencesStatement.execute();
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            connection.commit();
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getAllLinkedToConference :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public void update(ReportDto reportDto) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.update"))) {
            preparedStatement.setString(1, reportDto.getThemeEn());
            preparedStatement.setString(2, reportDto.getThemeUa());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(reportDto.getDateTime()));
            preparedStatement.setLong(4, reportDto.getSpeakerId());
            preparedStatement.setLong(5, reportDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: update :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public Map<Long, List<Report>> getSpeakersReports(List<Long> speakersIds, String language) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("reports.select.by.speaker.id"))) {
            Map<Long, List<Report>> speakersReports = new HashMap<>();
            for (Long speakerId : speakersIds){
                List<Report> reports = new ArrayList<>();
                preparedStatement.setLong(1, speakerId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                    reports.add(reportMapper.mapToObject(resultSet, language));
                speakersReports.put(speakerId, reports);
            }
            return speakersReports;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getSpeakersReports :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public int getReportsAmount() {
        int reportsAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement conferenceStatement =
                     connection.prepareStatement(sqlStatementsBundle.getString("reports.count"))) {
            ResultSet resultSet = conferenceStatement.executeQuery();
            if (resultSet.next())
                reportsAmount = resultSet.getInt(1);
            return reportsAmount;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getReportsAmount :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public int getReportsAmountLinkedToConference(long conferenceId) {
        int reportAmount = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(sqlStatementsBundle.getString("reports.count.linked.to.conference"))) {
            preparedStatement.setLong(1, conferenceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                reportAmount = resultSet.getInt(1);
            return reportAmount;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getReportsAmountLinkedToConference :: " + exc);
            throw new RuntimeException();
        }
    }

    @Override
    public List<Report> getPaginatedReportsLinkedToConference(long conferenceId,
                                                              Integer begin,
                                                              Integer recordsPerPage,
                                                              String language) {
        ReportMapper reportMapper = new ReportMapper();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     sqlStatementsBundle.getString("reports.select.all.linked.to.conference.with.pagination"))) {
            List<Report> paginatedReportsList = new ArrayList<>();
            preparedStatement.setLong(1, conferenceId);
            preparedStatement.setInt(2, begin);
            preparedStatement.setInt(3, recordsPerPage);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                paginatedReportsList.add(reportMapper.mapToObject(resultSet, language));
            return paginatedReportsList;
        } catch (SQLException exc) {
            LOG.error("JdbcReportDao :: getPaginatedReportsLinkedToConference :: " + exc);
            throw new RuntimeException();
        }
    }
}
